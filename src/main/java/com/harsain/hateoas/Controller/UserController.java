package com.harsain.hateoas.Controller;

import com.harsain.hateoas.Model.User;
import com.harsain.hateoas.Repository.UserRepository;
import com.harsain.hateoas.Resource.UserResource;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * Created by harsain on 4/7/17.
 */
@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserRepository userRepository;

  @GetMapping
  public @ResponseBody
  Resources<UserResource> getUsers() {
    List<UserResource> userResourceList = userRepository.findAll().stream().map(UserResource::new).collect(Collectors.toList());

    return new Resources<>(userResourceList);
  }

  @GetMapping("/{id}")
  public @ResponseBody UserResource getUser(@PathVariable final Long id) {
    return new UserResource(userRepository.findOne(id));
  }

  @PostMapping
  public ResponseEntity<?> save(@RequestBody final User user) {
    User createdUser = userRepository.save(user);
    if (createdUser != null) {
      Link userLink = new UserResource(createdUser).getLink("self");
      return ResponseEntity.created(URI.create(userLink.getHref())).build();
    }
    return ResponseEntity.noContent().build();
  }
}
