package fr.greta.gitnews_app.model;

import com.google.gson.annotations.SerializedName;

public class GitUser {
    public int id;
    public String login;
    @SerializedName("avatar_url")//sert à recuperer la valeur rxact de l'api
    public String avatarUrl;
    public int score;
}
