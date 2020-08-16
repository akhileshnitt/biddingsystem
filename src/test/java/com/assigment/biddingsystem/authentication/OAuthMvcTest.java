package com.assigment.biddingsystem.authentication;

import com.assigment.biddingsystem.BiddingsystemApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebAppConfiguration
@SpringBootTest(classes = BiddingsystemApplication.class)
@ActiveProfiles("local")
public class OAuthMvcTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    private MockMvc mockMvc;

    private static final String CLIENT_ID = "cliente";
    private static final String CLIENT_SECRET = "secret";

    private static final String CONTENT_TYPE = "application/json;charset=UTF-8";


    private static final String ITEMCODE = "APsqA123";

    private static final String BIDAMOUNT = "100";



    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).addFilter(springSecurityFilterChain).build();
    }

    private String obtainAccessToken(String username, String password) throws Exception {
        final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "password");
        params.add("username", username);
        params.add("password", password);

        // @formatter:off

        ResultActions result = mockMvc.perform(post("/oauth/token")
                .params(params)
                .with(httpBasic(CLIENT_ID, CLIENT_SECRET))
                .accept(CONTENT_TYPE))
                .andExpect(status().isUnauthorized());


        // @formatter:on

        String resultString = result.andReturn().getResponse().getContentAsString();

        JacksonJsonParser jsonParser = new JacksonJsonParser();
        return jsonParser.parseMap(resultString).get("access_token").toString();
    }

    @Test
    public void testNotSecuredEndPoint() throws Exception {
        mockMvc.perform(get("/api/v1/auctions")).andExpect(status().isOk());
    }

    @Test
    public void tesSecuredEndPoint() throws Exception {


        String bidRequest = "{\"itemCode\":\"" + ITEMCODE + "\",\"bidAmount\":\"" + BIDAMOUNT + "\"}";
        ResultActions authorization = mockMvc.perform(post("/api/v1/placeBid")
                .header("Authorization", "Bearer " + "TestToken")
                .contentType(CONTENT_TYPE)
                .content(bidRequest)
                .accept(CONTENT_TYPE))
                .andExpect(status().isUnauthorized());
    }

}