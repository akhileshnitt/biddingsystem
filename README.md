# biddingsystem
The Buidding System

1. H2 db is used for testing
2. Liquibase is used to manage schema 
3. ModelMapper is used to map entity to dto

Auction API:
http://localhost:8080/auctions

<img src="https://github.com/akhileshnitt/biddingsystem/blob/master/screen/Screen%20Shot%202020-08-13%20at%2012.04.37%20PM.png" alt="drawing" width="1000" height="300"/>
http://localhost:8080/auctions?pageSize=5&pageNo=0
<img src="https://github.com/akhileshnitt/biddingsystem/blob/master/screen/Screen%20Shot%202020-08-13%20at%2012.05.27%20PM.png" alt="drawing" width="1000" height="300"/>


Oauth2
1. http://localhost:8080/api/v1/auctions ,  This Api does not need authentication
2.http://localhost:8080/api/v1/placeBid , This Api needs authentication
   Steps :
     1. generate bearer token
     
     2. pass access token to placeBid Api


4. Optimistic locking to work in distributed env

