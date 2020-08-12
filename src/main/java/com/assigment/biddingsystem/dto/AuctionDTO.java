package com.assigment.biddingsystem.dto;

public class AuctionDTO {

   private String itemCode;
   private int  basePrice;
   private String status;
   private int stepRate;


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

   public String getStatus() {
      return status;
   }

   public void setStatus(String status) {
      this.status = status;
   }

   public int getStepRate() {
      return stepRate;
   }

   public void setStepRate(int stepRate) {
      this.stepRate = stepRate;
   }

   @Override
   public String toString() {
      return "AuctionDTO{" +
              "itemCode='" + itemCode + '\'' +
              ", basePrice=" + basePrice +
              ", status='" + status + '\'' +
              ", stepRate=" + stepRate +
              '}';
   }
}
