package com.assigment.biddingsystem.service;

import com.assigment.biddingsystem.dto.AuctionDTO;
import com.assigment.biddingsystem.util.AuctionStatus;

import java.util.List;

public interface AuctionService {

    List<AuctionDTO> retrieveAllAuctions(AuctionStatus status, Integer pageNo, Integer pageSize, String sortBy);
}
