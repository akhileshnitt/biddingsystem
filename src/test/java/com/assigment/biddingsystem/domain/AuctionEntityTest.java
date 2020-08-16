package com.assigment.biddingsystem.domain;

import com.assigment.biddingsystem.repo.AuctionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class AuctionEntityTest {

    @Autowired
    AuctionRepository auctionRepository;

    @Test
    public void testConcurrentTransactions() {
        System.out.println("Auction 1 : ->" + getAuction1().getBidPrice());
        System.out.println("Auction 2 : ->" + getAuction2().getBidPrice());
    }

    private AuctionEntity getAuction2() {
        AuctionEntity auctionEntity = auctionRepository.findById(new Long(1)).get();

        auctionEntity.setBidPrice(auctionEntity.getBidPrice() + 300);
        System.out.println(Thread.currentThread().getId());
        return auctionEntity;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    private AuctionEntity getAuction1() {
        AuctionEntity auctionEntity = auctionRepository.findById(new Long(1)).get();

        auctionEntity.setBidPrice(auctionEntity.getBidPrice() + 100);
        auctionRepository.save(auctionEntity);
        System.out.println(Thread.currentThread().getId());
        return auctionEntity;
    }

}