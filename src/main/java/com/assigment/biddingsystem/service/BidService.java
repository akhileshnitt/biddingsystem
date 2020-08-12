package com.assigment.biddingsystem.service;

import com.assigment.biddingsystem.SearchService;
import com.assigment.biddingsystem.dto.BidRequest;
import com.assigment.biddingsystem.util.BidStatus;
import org.springframework.stereotype.Service;

@Service
public class BidService {



    private SearchService searchService;

    public BidService(SearchService searchService) {
        this.searchService = searchService;
    }

    public BidStatus processBid(BidRequest bidRequest) {

        if(searchService.searchByItemCode(bidRequest.getItemCode()) == null){
            return BidStatus.NOTFOUND;
        }
        return BidStatus.ACCEPTED;
    }
}
