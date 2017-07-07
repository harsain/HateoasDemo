package com.harsain.hateoas;

import com.harsain.hateoas.Controller.TweetController;
import com.harsain.hateoas.Model.Tweet;
import com.harsain.hateoas.Model.User;
import com.harsain.hateoas.Repository.TweetRepository;
import com.harsain.hateoas.Repository.UserRepository;
import java.util.Arrays;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HateoasApplication {

  public static void main(String[] args) {
    SpringApplication.run(HateoasApplication.class, args);
  }

  @Bean
  CommandLineRunner init(UserRepository userRepository, TweetRepository tweetRepository) {
    return (evt) -> Arrays.asList("user1", "user2", "user3").forEach(
        name -> {
          User user = new User();
          user.setName(name);
          userRepository.save(user);
					Tweet tweet1 = new Tweet();
					tweet1.setLikes(0);
					tweet1.setAuthor(user);
					tweet1.setContent(user.getName() + "'s first tweet");
					tweetRepository.save(tweet1);

          Tweet tweet2 = new Tweet();
          tweet2.setLikes(0);
          tweet2.setAuthor(user);
          tweet2.setContent(user.getName() + "'s second tweet");
          tweetRepository.save(tweet2);
        }
    );
  }
}
