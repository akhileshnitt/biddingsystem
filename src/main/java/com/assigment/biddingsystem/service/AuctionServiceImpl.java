package com.assigment.biddingsystem.service;

import com.assigment.biddingsystem.domain.AuctionEntity;
import com.assigment.biddingsystem.dto.AuctionDTO;
import com.assigment.biddingsystem.repo.AuctionRepository;
import com.assigment.biddingsystem.util.AuctionStatus;
import com.assigment.biddingsystem.util.ObjectMapperUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuctionServiceImpl implements AuctionService{

    private static Logger logger = LoggerFactory.getLogger(AuctionServiceImpl.class);
    @Autowired
    private AuctionRepository auctionRepository;


    @Override
    public List<AuctionDTO> retrieveAllAuctions(AuctionStatus status, Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<AuctionEntity> auctionResults = auctionRepository.findAll(paging);

        if(auctionResults.hasContent()) {
            return ObjectMapperUtils.mapAll(auctionResults.getContent(), AuctionDTO.class);
        } else {
            logger.info("No auction found");
            return new ArrayList<AuctionDTO>();
        }
    }
}
