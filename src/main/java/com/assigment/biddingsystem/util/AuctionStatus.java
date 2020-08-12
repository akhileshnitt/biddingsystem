package com.assigment.biddingsystem.util;

public enum AuctionStatus {
    NEW("NEW"),
    RUNNING("RUNNING"),
    OVER("OVER");


    private String status;
    AuctionStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
