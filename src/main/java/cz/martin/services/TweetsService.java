package cz.martin.services;

import cz.martin.models.Tweet;
import cz.martin.repositories.TweetsRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.List;

@ApplicationScoped
@Named
public class TweetsService {
    @Inject
    private TweetsRepository tweetsRepository;

    public void createTweet(Tweet tweet) {
        this.tweetsRepository.createTweet(tweet);
    }

    public void deleteTweet(int id) {
        this.tweetsRepository.deleteTweet(id);
    }
    public List<Tweet> getTweets() {
        return this.tweetsRepository.getTweets();
    }
}
