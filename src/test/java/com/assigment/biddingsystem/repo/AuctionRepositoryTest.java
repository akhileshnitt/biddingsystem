package com.assigment.biddingsystem.repo;

import com.assigment.biddingsystem.domain.AuctionEntity;
import com.assigment.biddingsystem.util.AuctionStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@SpringBootTest
@DataJpaTest
class AuctionRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AuctionRepository auctionRepository;

    @Test
    void whenFindByItemCode_thenReturnAuctionEntity(){
        // given
        AuctionEntity auctionEntity =  new AuctionEntity();
        auctionEntity.setItemid(1L);
        auctionEntity.setItemCode("TestItem");
        auctionEntity.setBasePrice(2000);
        auctionEntity.setBidPrice(0);
        auctionEntity.setStepRate(100);
        auctionEntity.setStatus(AuctionStatus.RUNNING.getStatus());

        entityManager.persist(auctionEntity);
        entityManager.flush();

        //when

        AuctionEntity found = auctionRepository.findByItemCode("TestItem");

        //then
        assertEquals(found.getItemCode(),auctionEntity.getItemCode());


    }


    @Test
    void whenFindByWrongItemCode_thenReturnNull(){
        // given
        AuctionEntity auctionEntity =  new AuctionEntity();
        auctionEntity.setItemid(1L);
        auctionEntity.setItemCode("TestItem");
        auctionEntity.setBasePrice(2000);
        auctionEntity.setBidPrice(0);
        auctionEntity.setStepRate(100);
        auctionEntity.setStatus(AuctionStatus.RUNNING.getStatus());

        entityManager.persist(auctionEntity);
        entityManager.flush();

        //when

        AuctionEntity found = auctionRepository.findByItemCode("TestItemNotExist");

        //then
        assertEquals(found,null);


    }
}