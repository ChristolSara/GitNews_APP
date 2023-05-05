package fr.greta.gitnews_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import fr.greta.gitnews_app.model.GitUser;
import fr.greta.gitnews_app.model.GitUsersRespense;
import fr.greta.gitnews_app.service.GitRepoServiceAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //récupérer les views
        EditText editTextQuery = findViewById(R.id.editTextQuery);
        Button buttonSearch = findViewById(R.id.buttonSearch);
        ListView listViewUsers = findViewById(R.id.listViewUsers);

        /////préparer les données
        List<String> data =new ArrayList<>();
       final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,data);
        listViewUsers.setAdapter(arrayAdapter);

        //retrofit :faire appel à notree api avec retrofit
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
        //on click button

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //recupérer l'insert de user
                String query = editTextQuery.getText().toString();
                //on utilise Retrofit
                GitRepoServiceAPI gitRepoServiceAPI = retrofit.create(GitRepoServiceAPI.class);
                Call<GitUsersRespense> callGitUsers=gitRepoServiceAPI.searcheUsers(query);

                //envoyer la requette asynchron
                callGitUsers.enqueue(new Callback<GitUsersRespense>() {
                    @Override
                    public void onResponse(Call<GitUsersRespense> call, Response<GitUsersRespense> response) {
                        if(!response.isSuccessful()){
                            Log.i("info",String.valueOf(response.code()));
                            return;
                        }else{
                            GitUsersRespense gitUsersRespense=response.body();
                            for(GitUser user:gitUsersRespense.users){
                                data.add(user.login);
                            }
                            arrayAdapter.notifyDataSetChanged();
                        }

                    }
                    @Override
                    public void onFailure(Call<GitUsersRespense> call, Throwable t) {

                        Log.e("error","error");
                    }
                });


            }
        });

    }
}