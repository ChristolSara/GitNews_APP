package fr.greta.gitnews_app.service;

import fr.greta.gitnews_app.model.GitUsersRespense;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GitRepoServiceAPI {
    @GET("search/users")
    public Call<GitUsersRespense> searcheUsers(@Query("q") String query);
}
