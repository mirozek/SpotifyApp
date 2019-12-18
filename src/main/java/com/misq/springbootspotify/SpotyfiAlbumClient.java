package com.misq.springbootspotify;

import com.misq.springbootspotify.model.SpotyfiAlbum;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class SpotyfiAlbumClient {

    @GetMapping("/album/{authorName}")
    public SpotyfiAlbum getAlbumsByAuthor(OAuth2Authentication details, @PathVariable String authorName) {




        String jwt = ((OAuth2AuthenticationDetails)details.getDetails()).getTokenValue();

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + jwt);
        HttpEntity httpEntity = new HttpEntity(httpHeaders);


        //DO SPRAWDZENIA
        ResponseEntity<SpotyfiAlbum> exchange = restTemplate.exchange("https://api.spotify.com/v1/search?q="+ authorName +"&type=track&market=US&limit=10&offset=5",
                HttpMethod.GET,
                httpEntity,
                SpotyfiAlbum.class);
        return exchange.getBody();
    }

}
