package com.harsain.hateoas.Users;

import com.harsain.hateoas.HateoasApplication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserResourceAssembler userResourceAssembler;

    @GetMapping
    public Resources<Resource<User>> allUsers() {
        log.info("Request for allUser received");
        List<Resource<User>> resourceList = userService.getAll().stream().map( userResourceAssembler::toResource ).collect(Collectors.toList());

        return new Resources<>(resourceList,
                linkTo(methodOn(UserController.class).allUsers()).withRel("users"));
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User newUser) throws URISyntaxException {
        log.info("Request for createUser received");

        Resource<User> resource = userResourceAssembler.toResource(userService.createUser(newUser));

        return ResponseEntity
                .created(new URI(resource.getId().expand().getHref()))
                .body(resource);

    }

    @GetMapping("/{id}")
    public Resource<User> getUserById(@PathVariable int id) {
        log.info("Request for getUserById received");

        return userResourceAssembler.toResource(userService.getUserById(id));
    }
}
