package com.assigment.biddingsystem.service;

import com.assigment.biddingsystem.cache.repo.AuctionCacheRepository;
import com.assigment.biddingsystem.domain.AuctionEntity;
import com.assigment.biddingsystem.domain.BidInfo;
import com.assigment.biddingsystem.dto.BidRequest;
import com.assigment.biddingsystem.repo.AuctionRepository;
import com.assigment.biddingsystem.repo.BidInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
public class BidHandler {

    private static Logger logger = LoggerFactory.getLogger(BidHandler.class);

    private AuctionRepository auctionRepository;

    private BidInfoRepository bidInfoRepository;

    public BidHandler(AuctionRepository auctionRepository,
                      BidInfoRepository bidInfoRepository,
                      AuctionCacheRepository auctionCacheRepository) {
        this.auctionRepository = auctionRepository;
        this.bidInfoRepository = bidInfoRepository;
    }

    public AuctionEntity searchByItemCode(String itemCode){
        AuctionEntity auctionEntity = auctionRepository.findByItemCode(itemCode);
        return auctionEntity;
    }

    public AuctionEntity persisBid(AuctionEntity auctionEntity, BidRequest bidRequest) {
        logger.info("Persisting bid with {}",bidRequest.getBidAmount());
        auctionEntity.setBidPrice(bidRequest.getBidAmount());
        auctionRepository.save(auctionEntity);
        return auctionEntity;

    }


    public void recordBidInfo(AuctionEntity auctionEntity, BidRequest bidRequest) {
        bidInfoRepository.save(new BidInfo(auctionEntity.getItemid(),
                ((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername(),
                bidRequest.getBidAmount()));
    }
}
