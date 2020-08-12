package com.assigment.biddingsystem.dto;

import com.assigment.biddingsystem.domain.AuctionEntity;
import com.assigment.biddingsystem.util.AuctionStatus;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuctionDTOTest {

    private static final ModelMapper modelMapper = new ModelMapper();

    @Test
    public void checkAuctionMapping() {
        AuctionEntity auctionEntity =  new AuctionEntity();
        auctionEntity.setItemid(1L);
        auctionEntity.setItemCode("TestItem");
        auctionEntity.setBasePrice(2000);
        auctionEntity.setBidPrice(0);
        auctionEntity.setStepRate(100);
        auctionEntity.setStatus(AuctionStatus.RUNNING.getStatus());


        AuctionDTO auctionDTO = modelMapper.map(auctionEntity,AuctionDTO.class);
        assertEquals(auctionDTO.getItemCode(), auctionEntity.getItemCode());
        assertEquals(auctionDTO.getBasePrice(), auctionEntity.getBasePrice());
        assertEquals(auctionDTO.getStepRate(), auctionEntity.getStepRate());
        assertEquals(auctionDTO.getStatus(), auctionEntity.getStatus());

    }

}