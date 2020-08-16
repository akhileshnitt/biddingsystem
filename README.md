# biddingsystem
The Buidding System

1. H2 db is used for testing
2. Liquibase is used to manage schema 
3. ModelMapper is used to map entity to dto
4. @Version is used to imlement Concurrency
5. Redis is used  as write back cache
6. Oauth2 is used for securing Api basead on Token
7. Spring AOP is used for Logging aspect

## Steps to execute 
 * download or clone the project
 * cd to biddingsystem
 *  ./gradle  clean build
 * once build is successfull 
      * start Redis by executing docker-compose up --build
      * run application using  java -jar build/libs/biddingsystem-0.0.1-SNAPSHOT.jar 

 ##### Test API (using postman)
 * Auction API: (this api does not need authenticantion)
    * http://localhost:8080/api/v1/auctions
    * http://localhost:8080/api/v1/auctions?pageSize=5&pageNo=0
    
 * Steps to Exeucte palceBidApi   
    * Post http://localhost:8080/oauth/token with below param-
      * in Authorization section 
         * username : cliente
         * password : password
      * in Body Section
         * password :  secret
         * username : user
         * password : password
         * x-www-form-urlencoded
         
     * Once request is successfull ,below respose will be returned by Oauth Server
          ```{
               "access_token": "5758ca98-2c19-43bb-bd63-fbc4dccf32f7",
               "token_type": "bearer",
               "refresh_token": "94b5e410-5861-4e4f-a7a7-a6e89035bff5",
               "expires_in": 43199,
               "scope": "read write"
             }
             ```
     * post http://localhost:8080/api/v1/placeBid with below param- 
        * In Header section pass access_token obtained in previous step
            * Authorization : "5758ca98-2c19-43bb-bd63-fbc4dccf32f7"
            * Content-Type : application/json
        Request Body : ```{
                             "itemCode":"APsqA123",
                              "bidAmount":"8000"
                           }
                           ```
                           
<img src="https://github.com/akhileshnitt/biddingsystem/blob/master/screen/Screen%20Shot%202020-08-13%20at%2012.04.37%20PM.png" alt="drawing" width="1000" height="300"/>

 * http://localhost:8080/auctions?pageSize=5&pageNo=0
<img src="https://github.com/akhileshnitt/biddingsystem/blob/master/screen/Screen%20Shot%202020-08-13%20at%2012.05.27%20PM.png" alt="drawing" width="1000" height="300"/>


#### Oauth2
* http://localhost:8080/api/v1/auctions ,  This Api does not need authentication</br>
* http://localhost:8080/api/v1/placeBid , This Api needs authentication</br>
   * Steps To call Api:<br>
     * Generate Access and Bearer token
     <img src="https://github.com/akhileshnitt/biddingsystem/blob/master/screen/Screen%20Shot%202020-08-13%20at%2012.39.51%20PM.png" alt="drawing" width="1000" height="300"/>
    
     
     * Pass access token to placeBid Api
     <img src="https://github.com/akhileshnitt/biddingsystem/blob/master/screen/Screen%20Shot%202020-08-13%20at%2012.48.20%20PM.png" alt="drawing" width="1000" height="300"/>


4. Optimistic locking to work in distributed env

