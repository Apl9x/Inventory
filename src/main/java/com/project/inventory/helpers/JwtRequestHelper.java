package com.project.inventory.helpers;

import com.project.inventory.entities.Role;
import com.project.inventory.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
public class JwtRequestHelper {
    private static JwtProvider jwtProvider;

    private JwtProvider applicationUnderTestJwtProvider;

    public JwtRequestHelper(JwtProvider applicationUnderTestJwtProvider) {
        this.applicationUnderTestJwtProvider = applicationUnderTestJwtProvider;
    }

    @PostConstruct
    private void initProvider () {
        jwtProvider = applicationUnderTestJwtProvider;
    }


    /**
     * Build Request entity for logged in user with a payload.
     *
     * @param username
     * @param withRoleId
     * @param payload
     * @return
     */
    public static HttpEntity loggedInAs(String username, String withRoleId, Object payload) {
        return new HttpEntity(payload, forUser(username, withRoleId));
    }

    /**
     * Build Request entity for logged in user, no payload.
     *
     * @param username
     * @param withRoleId
     * @return
     */
    public static HttpEntity loggedInAs(String username, String withRoleId) {
        return new HttpEntity(forUser(username, withRoleId));
    }

    /**
     * Generate the appropriate headers for JWT Authentication.
     *
     * @param username username
     * @param withRoleId role identifier
     * @return Http Headers for Content-Type and Authorization
     */
    private static HttpHeaders forUser(String username, String withRoleId){
        HttpHeaders headers = new HttpHeaders();
        Role r = new Role();
        r.setRoleName(withRoleId);
        String token =  jwtProvider.createToken(username, Arrays.asList(r));
        headers.setContentType(APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + token);
        return headers;
    }
}