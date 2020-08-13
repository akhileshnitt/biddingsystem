package com.assigment.biddingsystem.controller;

import com.assigment.biddingsystem.dto.AuctionDTO;
import com.assigment.biddingsystem.dto.BidRequest;
import com.assigment.biddingsystem.service.AuctionService;
import com.assigment.biddingsystem.service.BidService;
import com.assigment.biddingsystem.util.AuctionStatus;
import com.assigment.biddingsystem.util.BidStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
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
public class AuctionController extends ResourceServerConfigurerAdapter {


    private AuctionService auctionService;
    private BidService bidService;

    public AuctionController(AuctionService auctionService,
                             BidService bidService) {
        this.auctionService = auctionService;
        this.bidService  = bidService;

    }

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
        System.out.println(bidRequest);
        BidStatus bidStatus = bidService.processBid(bidRequest);
        return new ResponseEntity<>(bidStatus.getReason(), new HttpHeaders(), bidStatus.getCode());

    }


    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().antMatchers("/oauth/token", "/oauth/authorize**", "/api/v1/auctions").permitAll();
//			 .anyRequest().authenticated();
        http.requestMatchers().antMatchers("/api/v1/placeBid")
                .and().authorizeRequests()
                .antMatchers("/api/v1/placeBid").access("hasRole('USER')")
                .and().requestMatchers().antMatchers("/admin")
                .and().authorizeRequests()
                .antMatchers("/admin").access("hasRole('ADMIN')");
    }

}
