package cz.martin.beans;

import cz.martin.models.Tweet;
import cz.martin.services.TweetsService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.IOException;

@Named
@RequestScoped
public class NewTweetBean {
    @Inject
    private TweetsService tweetsService;


    private Tweet newTweet = new Tweet("", "");

    public void createNewTweet() throws IOException {
        this.tweetsService.createTweet(newTweet);
        FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
    }

    public Tweet getNewTweet() {
        return newTweet;
    }
}
