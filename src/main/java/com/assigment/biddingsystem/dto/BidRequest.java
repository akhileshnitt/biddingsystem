package com.assigment.biddingsystem.dto;

public class BidRequest {

    private String itemCode;
    private int bidAmount;

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public int getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(int bidAmount) {
        this.bidAmount = bidAmount;
    }

    @Override
    public String toString() {
        return "BidRequest{" +
                "itemCode='" + itemCode + '\'' +
                ", bidAmount=" + bidAmount +
                '}';
    }
}
