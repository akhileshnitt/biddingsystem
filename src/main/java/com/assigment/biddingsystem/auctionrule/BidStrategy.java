package com.assigment.biddingsystem.auctionrule;

public interface BidStrategy<T,U> {
    public boolean apply(T t,U v);
}
