package com.assigment.biddingsystem.repo;

import com.assigment.biddingsystem.domain.BidInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class BidInfoRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BidInfoRepository bidInfoRepository;

    @Test
    void whenFindByItemId_thenReturnBidInfo(){
        // given
        BidInfo bidInfo =  new BidInfo();
        bidInfo.setId(1);
        bidInfo.setItemId(1L);
        bidInfo.setUserName("Test");
        bidInfo.setBidPrice(0);


        entityManager.persist(bidInfo);
        entityManager.flush();

        //when

        BidInfo found = bidInfoRepository.findById(1).get();

        //then
        assertEquals(found.getId(),bidInfo.getId());
    }

    @Test
    void whenFindByWrongItemId_thenReturnNullBidInfo(){
        // given
        BidInfo bidInfo =  new BidInfo();
        bidInfo.setId(1);
        bidInfo.setItemId(1L);
        bidInfo.setUserName("Test");
        bidInfo.setBidPrice(0);


        entityManager.persist(bidInfo);
        entityManager.flush();

        //when

        Optional<BidInfo> found = bidInfoRepository.findById(2);

        //then
        assertEquals(found.isPresent(),false);
    }

}