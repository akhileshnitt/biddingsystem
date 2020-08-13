package com.assigment.biddingsystem.service;

import com.assigment.biddingsystem.domain.AuctionEntity;
import com.assigment.biddingsystem.dto.BidRequest;
import com.assigment.biddingsystem.util.BidStatus;
import org.springframework.stereotype.Service;

import javax.persistence.OptimisticLockException;

@Service
public class BidService {

    private BidHandler bidHandler;

    public BidService(BidHandler bidHandler) {
        this.bidHandler = bidHandler;
    }


    public BidStatus processBid(BidRequest bidRequest) {
        AuctionEntity auctionEntity = bidHandler.searchByItemCode(bidRequest.getItemCode());
        
        // if  itemcode does not exist
        if(auctionEntity == null) return BidStatus.NOTFOUND;
        
        //if  bid is not valid
        if(!isValidBid(auctionEntity,bidRequest)) return BidStatus.REJECTED;

        // persist bid in db
        try {
            bidHandler.persisBid(auctionEntity,bidRequest);
            return BidStatus.ACCEPTED;
        } catch (OptimisticLockException e) {
            return BidStatus.REJECTED;
        } catch(Exception e){
            System.out.println("Error in processing bid request");
            return BidStatus.ERROR_PROCESSING;
        }

    }

    private boolean isValidBid(AuctionEntity auctionEntity, BidRequest bidRequest) {
        //first bid
        if(auctionEntity.getBidPrice() == 0){
            return bidRequest.getBidAmount() > auctionEntity.getBasePrice()+auctionEntity.getStepRate();
        }
        return bidRequest.getBidAmount() > auctionEntity.getBidPrice()+auctionEntity.getStepRate();

    }
}
