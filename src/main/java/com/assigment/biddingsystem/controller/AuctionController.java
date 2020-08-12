package com.assigment.biddingsystem.controller;

import com.assigment.biddingsystem.dto.AuctionDTO;
import com.assigment.biddingsystem.service.AuctionService;
import com.assigment.biddingsystem.util.AuctionStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuctionController {


    private AuctionService auctionService;

    public AuctionController(AuctionService auctionService) {
        this.auctionService = auctionService;
    }

    @GetMapping("/greeting")
    public String greeting(){
        return "sdfsdfsdfds";
    }

    @GetMapping("/auctions")
    public ResponseEntity<List<AuctionDTO>> auctions(@RequestParam(defaultValue = "0") Integer pageNo,
                                                     @RequestParam(defaultValue = "2") Integer pageSize,
                                                     @RequestParam(defaultValue = "baseprice") String sortBy){

        List<AuctionDTO> aunctions = auctionService.retrieveAllAuctions(AuctionStatus.RUNNING, pageNo, pageSize, sortBy);
        return new ResponseEntity<List<AuctionDTO>>(aunctions,
                new HttpHeaders(), HttpStatus.OK);
    }
}
