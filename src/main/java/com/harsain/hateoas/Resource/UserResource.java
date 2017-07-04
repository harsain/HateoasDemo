package com.harsain.hateoas.Resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import com.harsain.hateoas.Controller.TweetController;
import com.harsain.hateoas.Controller.UserController;
import com.harsain.hateoas.Model.User;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

/**
 * Created by harsain on 4/7/17.
 */
public class UserResource extends ResourceSupport {
  private final User user;


  public UserResource(User user) {
    this.user = user;
    this.add(linkTo(methodOn(UserController.class).getUser(user.getId())).withSelfRel());
    this.add(linkTo(methodOn(TweetController.class, user.getName()).getAll(user.getName())).withRel("all-tweets"));
  }

  public User getUser() {
    return user;
  }
}
