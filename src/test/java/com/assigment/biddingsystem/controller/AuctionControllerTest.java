package com.assigment.biddingsystem.controller;

import com.assigment.biddingsystem.dto.AuctionDTO;
import com.assigment.biddingsystem.service.AuctionServiceImpl;
import com.assigment.biddingsystem.service.BidService;
import com.assigment.biddingsystem.util.AuctionStatus;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AuctionController.class)
class AuctionControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AuctionServiceImpl auctionService;

    @MockBean
    private BidService bidService;

    @Test
    public void givenAuctions_whenGetAuction_thenReturnJsonArray()
            throws Exception {

        AuctionDTO auction = new AuctionDTO();
        auction.setItemCode("Test");
        auction.setBasePrice(100);
        auction.setStatus("Running");
        auction.setStepRate(100);


        List<AuctionDTO> auctionDTOS = Arrays.asList(auction);

        given(auctionService.retrieveAllAuctions(AuctionStatus.RUNNING,0,5,"itemCode")).willReturn(auctionDTOS);

        mvc.perform(get("/api/v1/auctions")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

}