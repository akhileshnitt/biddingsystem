package com.assigment.biddingsystem.util;

import org.springframework.http.HttpStatus;

public enum BidStatus {
    ACCEPTED("Bid is accepted", HttpStatus.OK),
    NOTFOUND("Auction not found",HttpStatus.NOT_FOUND),
    REJECTED("Bid is rejected",HttpStatus.NOT_ACCEPTABLE);

    private String reason;
    private HttpStatus code;

    BidStatus(String reason, HttpStatus code) {
        this.reason = reason;
        this.code = code;
    }

    public String getReason() {
        return reason;
    }

    public HttpStatus getCode() {
        return code;
    }
}
