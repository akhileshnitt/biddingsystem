package com.assigment.biddingsystem.cache.repo;

import com.assigment.biddingsystem.cache.cacheable.AuctionCache;
import com.assigment.biddingsystem.domain.AuctionEntity;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AuctionCacheRepository {

    private RedisTemplate<String, AuctionCache> redisTemplate;
    private HashOperations hashOperations; //to access Redis cache

    public AuctionCacheRepository(RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
        this.hashOperations = this.redisTemplate.opsForHash();
    }

    public void save(AuctionEntity auction){
        hashOperations.put("AUCTION", auction.getItemCode(), auction);
    }


    public AuctionEntity findByItemCode(String itemCode){
        return (AuctionEntity) hashOperations.get("AUCTION", itemCode);
    }

    public void update(AuctionEntity auction){
        save(auction);
    }

    public void delete(String itemCode){
        hashOperations.delete("AUCTION", itemCode);
    }
}
