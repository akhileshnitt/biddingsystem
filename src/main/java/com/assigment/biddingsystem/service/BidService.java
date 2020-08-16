package com.assigment.biddingsystem.service;

import com.assigment.biddingsystem.auctionrule.BidStrategy;
import com.assigment.biddingsystem.cache.repo.AuctionCacheRepository;
import com.assigment.biddingsystem.domain.AuctionEntity;
import com.assigment.biddingsystem.dto.BidRequest;
import com.assigment.biddingsystem.util.BidStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.OptimisticLockException;

@Service
public class BidService {

    private static Logger logger = LoggerFactory.getLogger(BidService.class);

    private BidHandler bidHandler;
    private BidStrategy<AuctionEntity, BidRequest> bidStrategy;
    private AuctionCacheRepository auctionCacheRepository;


    public BidService(BidHandler bidHandler, BidStrategy<AuctionEntity, BidRequest> bidStrategy,AuctionCacheRepository auctionCacheRepository) {
        this.bidHandler = bidHandler;
        this.bidStrategy = bidStrategy;
        this.auctionCacheRepository = auctionCacheRepository;
    }


    public BidStatus processBid(BidRequest bidRequest) {
        //retrive from cache
        AuctionEntity auctionEntity = auctionCacheRepository.findByItemCode(bidRequest.getItemCode());
        if(auctionEntity == null) {
            auctionEntity = bidHandler.searchByItemCode(bidRequest.getItemCode());
        }
        
        // if  itemcode does not exist
        if(auctionEntity == null) return BidStatus.NOTFOUND;

        //record bid
        bidHandler.recordBidInfo(auctionEntity,bidRequest);
        
        //if  bid is not valid
        if(!bidStrategy.apply(auctionEntity,bidRequest)) return BidStatus.REJECTED;

        // persist bid in db
        try {
            AuctionEntity updatedAuctionEntity = bidHandler.persisBid(auctionEntity,bidRequest);
            //if bid is accepted update cache
            auctionCacheRepository.save(updatedAuctionEntity);
            return BidStatus.ACCEPTED;
        } catch (OptimisticLockException e) {
            return BidStatus.REJECTED;
        } catch(Exception e){
            //e.printStackTrace();
            logger.error("Error in processing bid request :{}",e.getCause());
            return BidStatus.ERROR_PROCESSING;
        }

    }

}
