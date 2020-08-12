package com.assigment.biddingsystem.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name="AUCTION")
public class AuctionEntity {

    @Id
    @GeneratedValue
    private Long itemid;

    @Column(name="itemcode")
    private String itemCode;

    @Column(name="baseprice")
    private int basePrice;

    @Column(name="bidprice")
    private int bidPrice;

    @Column(name="steprate")
    private int stepRate;

    @Column(name="status")
    private String status;

    @Version
    @Column(name="VERSION")
    private Integer version;


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


}
