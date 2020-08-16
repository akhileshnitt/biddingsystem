package com.assigment.biddingsystem.auctionrule;

import com.assigment.biddingsystem.domain.AuctionEntity;
import com.assigment.biddingsystem.dto.BidRequest;
import org.springframework.stereotype.Component;

@Component
public class StepRuleStrategy implements BidStrategy<AuctionEntity, BidRequest> {

    @Override
    public boolean apply(AuctionEntity auctionEntity,BidRequest bidRequest) {
        //first bid
        if(auctionEntity.getBidPrice() == 0){
            return bidRequest.getBidAmount() > auctionEntity.getBasePrice()+auctionEntity.getStepRate();
        }
        return bidRequest.getBidAmount() > auctionEntity.getBidPrice()+auctionEntity.getStepRate();
    }
}
