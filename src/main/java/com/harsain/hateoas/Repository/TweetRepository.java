package com.harsain.hateoas.Repository;

import com.harsain.hateoas.Model.Tweet;
import com.harsain.hateoas.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import javax.swing.text.html.Option;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Created by harsain on 3/7/17.
 */
public interface TweetRepository extends CrudRepository<Tweet, Long> {

  Optional<Tweet> findById(Long id);

  List<Tweet> findByAuthor(User user);
}
