package com.assigment.biddingsystem.cache.cacheable;

import java.io.Serializable;

public class AuctionCache implements Serializable {

    private Long itemid;
    private String itemCode;
    private int basePrice;
    private int bidPrice;
    private int stepRate;
    private String status;
    private Integer version;


    public AuctionCache(Long itemid, String itemCode, int basePrice, int bidPrice, int stepRate, String status, Integer version) {
        this.itemid = itemid;
        this.itemCode = itemCode;
        this.basePrice = basePrice;
        this.bidPrice = bidPrice;
        this.stepRate = stepRate;
        this.status = status;
        this.version = version;
    }

    public Long getItemid() {
        return itemid;
    }

    public void setItemid(Long itemid) {
        this.itemid = itemid;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(int basePrice) {
        this.basePrice = basePrice;
    }

    public int getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(int bidPrice) {
        this.bidPrice = bidPrice;
    }

    public int getStepRate() {
        return stepRate;
    }

    public void setStepRate(int stepRate) {
        this.stepRate = stepRate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
