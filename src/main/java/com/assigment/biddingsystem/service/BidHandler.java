package com.assigment.biddingsystem.service;

import com.assigment.biddingsystem.domain.AuctionEntity;
import com.assigment.biddingsystem.dto.BidRequest;
import com.assigment.biddingsystem.repo.AuctionRepository;
import org.springframework.stereotype.Service;

@Service
public class BidHandler {


    private AuctionRepository auctionRepository;

    public BidHandler(AuctionRepository auctionRepository) {
        this.auctionRepository = auctionRepository;
    }

    public AuctionEntity searchByItemCode(String itemCode){
        AuctionEntity auctionEntity = auctionRepository.findByItemCode(itemCode);
        return auctionEntity;
    }

    public void persisBid(AuctionEntity auctionEntity, BidRequest bidRequest) {
        auctionEntity.setBidPrice(bidRequest.getBidAmount());
        auctionRepository.save(auctionEntity);

    }


}
