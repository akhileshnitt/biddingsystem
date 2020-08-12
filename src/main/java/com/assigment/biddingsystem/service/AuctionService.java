package com.assigment.biddingsystem.service;

import com.assigment.biddingsystem.domain.AuctionEntity;
import com.assigment.biddingsystem.dto.AuctionDTO;
import com.assigment.biddingsystem.repo.AuctionRepository;
import com.assigment.biddingsystem.util.AuctionStatus;
import com.assigment.biddingsystem.util.ObjectMapperUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuctionService {

    private AuctionRepository auctionRepository;


    public AuctionService(AuctionRepository auctionRepository) {
        this.auctionRepository = auctionRepository;
    }

    public List<AuctionDTO> retrieveAllAuctions(AuctionStatus status, Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<AuctionEntity> auctionResults = auctionRepository.findAll(paging);

        if(auctionResults.hasContent()) {
            return ObjectMapperUtils.mapAll(auctionResults.getContent(), AuctionDTO.class);
        } else {
            return new ArrayList<AuctionDTO>();
        }
    }
}
