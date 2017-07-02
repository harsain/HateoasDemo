package com.harsain.hateoas.Controller;

import com.harsain.hateoas.Model.Tweet;
import com.harsain.hateoas.Repository.TweetRepository;
import com.harsain.hateoas.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by harsain on 3/7/17.
 */

@Controller
@RequestMapping("/tweet")
public class TweetController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TweetRepository tweetRepository;

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public class NotFoundException extends RuntimeException {}

    @GetMapping(value = "/{id}")
    public @ResponseBody
    Tweet getTweet(@PathVariable Long id) {
        return tweetRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @GetMapping
    public @ResponseBody List<Tweet> getAll() {
        Iterator<Tweet> iterator = tweetRepository.findAll().iterator();
        List<Tweet> tweets = new ArrayList<>();
        iterator.forEachRemaining(tweets::add);

        return tweets;
    }

    @PostMapping
    public ResponseEntity<?> add(@PathVariable String userId, @RequestBody Tweet tweet) {
        return this.userRepository.findById(userId).map( user ->  {
            Tweet createdTweet = tweetRepository.save(tweet);

            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(createdTweet.getId()).toUri();
            return ResponseEntity.created(location).build();
        })
        .orElse(ResponseEntity.noContent().build());
    }
}
