package com.harsain.hateoas.Resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import com.harsain.hateoas.Controller.TweetController;
import com.harsain.hateoas.Model.Tweet;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

/**
 * Created by harsain on 4/7/17.
 */
public class TweetResource extends ResourceSupport {

  private final Tweet tweet;

  public TweetResource(Tweet tweet) {
    String author = tweet.getAuthor().getName();
    this.tweet = tweet;
    this.add(linkTo(TweetController.class, author).withRel("all-tweets"));
    this.add(linkTo(methodOn(TweetController.class, author).getLikes(author, tweet.getId())).withRel("likes-count"));
    this.add(linkTo(methodOn(TweetController.class, author).getTweet(author, tweet.getId()))
        .withSelfRel());
  }

  public Tweet getTweet() {
    return tweet;
  }
}
