package com.assigment.biddingsystem.repo;


import com.assigment.biddingsystem.domain.AuctionEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AuctionRepository extends PagingAndSortingRepository<AuctionEntity, Integer> {

}
