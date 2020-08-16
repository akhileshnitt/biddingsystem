package com.assigment.biddingsystem.service;

import com.assigment.biddingsystem.domain.AuctionEntity;
import com.assigment.biddingsystem.dto.AuctionDTO;
import com.assigment.biddingsystem.repo.AuctionRepository;
import com.assigment.biddingsystem.util.AuctionStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
public class AuctionServiceImplTest {

    @MockBean
    private  AuctionRepository auctionRepository;

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {

        @Bean
        public AuctionService auctionService() {
            return new AuctionServiceImpl();
        }
    }

    @Autowired
    private AuctionService auctionService;

    @Before
    public  void setUp() {
        // given
        AuctionEntity auctionEntity =  new AuctionEntity();
        auctionEntity.setItemid(1L);
        auctionEntity.setItemCode("TestItem");
        auctionEntity.setBasePrice(2000);
        auctionEntity.setBidPrice(0);
        auctionEntity.setStepRate(100);
        auctionEntity.setStatus(AuctionStatus.RUNNING.getStatus());

        Pageable paging = PageRequest.of(0, 5);
        List<AuctionEntity> entities = new ArrayList<>();
        entities.add(auctionEntity);
        Page<AuctionEntity> pagedAuction = new PageImpl(entities);
        Mockito.when(auctionRepository.findAll(paging)).thenReturn(pagedAuction);
    }

    @Test
    public void retrieveAllAuctions() {
        List<AuctionDTO> allAuctions =  auctionService.retrieveAllAuctions(AuctionStatus.RUNNING,0,5,"ItemCode");
        assertEquals(allAuctions.size(),1);
        assertEquals(allAuctions.get(0).getItemCode(),"TestItem");
        assertEquals(allAuctions.get(0).getStepRate(),100);
        assertEquals(allAuctions.get(0).getStatus(),AuctionStatus.RUNNING.getStatus());


    }
}