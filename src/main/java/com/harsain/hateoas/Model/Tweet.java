package com.harsain.hateoas.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by harsain on 3/7/17.
 */

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
public @Data class Tweet {

    @Id
    @GeneratedValue
    private Long id;

    @JsonIgnore
    @ManyToOne
    private User author;

    private String content;
}
