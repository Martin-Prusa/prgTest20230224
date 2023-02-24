package cz.martin.beans;

import cz.martin.models.Tweet;
import cz.martin.services.TweetsService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.IOException;
import java.io.Serializable;

@SessionScoped
@Named
public class UpdateBean implements Serializable {
    @Inject
    private TweetsService tweetsService;

    private Tweet editedTweet = null;

    private int getId() {
        return Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id"));
    }

    public void edit() throws IOException {
        if(editedTweet != null) tweetsService.editTweet(editedTweet);
        FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
    }

    public Tweet getEditedTweet() {
        int id = getId();
        if(editedTweet == null || editedTweet.getId() != id) editedTweet = tweetsService.getTweetById(id);
        return editedTweet;
    }
}
