package com.assigment.biddingsystem.controller;

import com.assigment.biddingsystem.config.ResourceServerCustomConfiguration;
import com.assigment.biddingsystem.dto.AuctionDTO;
import com.assigment.biddingsystem.dto.BidRequest;
import com.assigment.biddingsystem.service.AuctionServiceImpl;
import com.assigment.biddingsystem.service.BidService;
import com.assigment.biddingsystem.util.AuctionStatus;
import com.assigment.biddingsystem.util.BidStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@EnableResourceServer
public class AuctionController  extends ResourceServerCustomConfiguration {

    private static Logger logger = LoggerFactory.getLogger(AuctionController.class);

    private AuctionServiceImpl auctionService;
    private BidService bidService;

    public AuctionController(AuctionServiceImpl auctionService,
                             BidService bidService) {
        this.auctionService = auctionService;
        this.bidService  = bidService;

    }



    public ResponseEntity<List<AuctionDTO>> defaultAuctions(@RequestParam(defaultValue = "0") Integer pageNo,
                                                     @RequestParam(defaultValue = "2") Integer pageSize,
                                                     @RequestParam(defaultValue = "baseprice") String sortBy) {

        return new ResponseEntity<>(null,
                new HttpHeaders(), HttpStatus.OK);
    }

    //@HystrixCommand(fallbackMethod = "defaultAuctions")
    @GetMapping("/auctions")
    public ResponseEntity<List<AuctionDTO>> auctions(@RequestParam(defaultValue = "0") Integer pageNo,
                                                     @RequestParam(defaultValue = "2") Integer pageSize,
                                                     @RequestParam(defaultValue = "baseprice") String sortBy) {
        List<AuctionDTO> aunctions = auctionService.retrieveAllAuctions(AuctionStatus.RUNNING, pageNo, pageSize, sortBy);

        return new ResponseEntity<>(aunctions,
                new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/placeBid")
    public ResponseEntity placeBid(@RequestBody BidRequest bidRequest) {
        logger.info(bidRequest.toString());
        BidStatus bidStatus = bidService.processBid(bidRequest);
        return new ResponseEntity<>(bidStatus.getReason(), new HttpHeaders(), bidStatus.getCode());

    }




}
