package com.harsain.hateoas.Controller;

import static org.springframework.hateoas.jaxrs.JaxRsLinkBuilder.linkTo;

import com.harsain.hateoas.Model.Tweet;
import com.harsain.hateoas.Repository.TweetRepository;
import com.harsain.hateoas.Repository.UserRepository;
import com.harsain.hateoas.Resource.TweetResource;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by harsain on 3/7/17.
 */

@Controller
@RequestMapping("{userName}/tweets")
public class TweetController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TweetRepository tweetRepository;

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public class NotFoundException extends RuntimeException {}

    @GetMapping(value = "/{id}")
    public @ResponseBody
    TweetResource getTweet(@PathVariable String userName, @PathVariable Long id) {
        return new TweetResource(tweetRepository.findOne(id));
    }

    @GetMapping
    public @ResponseBody List<TweetResource> getAll(@PathVariable String userName) {
        Iterable<Tweet> iterator = tweetRepository.findAll();
       List<TweetResource> tweetList = new ArrayList<>();
        iterator.forEach( tweet -> tweetList.add( new TweetResource(tweet)) );
        return tweetList;
    }

    @PostMapping
    public ResponseEntity<?> add(@PathVariable String userName, @RequestBody Tweet tweet) {
        return this.userRepository.findByName(userName).map( user ->  {
            Tweet createdTweet = tweetRepository.save(new Tweet(null, user, tweet.getContent()));
            Link tweetLink = new TweetResource(createdTweet).getLink("self");
            return ResponseEntity.created(URI.create(tweetLink.getHref())).build();
        })
        .orElse(ResponseEntity.noContent().build());
    }
}
