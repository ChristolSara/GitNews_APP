package fr.greta.gitnews_app.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GitUsersRespense {
    @SerializedName("total_count")
    public int totalCount;
    @SerializedName("items")
    public List<GitUser> users = new ArrayList<>();
}
