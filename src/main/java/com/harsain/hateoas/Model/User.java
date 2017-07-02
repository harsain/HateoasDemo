package com.harsain.hateoas.Model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by harsain on 3/7/17.
 */

@ToString
@EqualsAndHashCode
@Setter
@Getter
@Entity
public @Data class User {

    @OneToMany(mappedBy = "author")
    private Set<Tweet> tweets = new HashSet<>();

    @Id
    @GeneratedValue
    private Long id;

    private String name;
}
