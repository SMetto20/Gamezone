package com.moringaschool.thegamezone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import connection.GameClient;
import interfaces.GameApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GamesActivity extends AppCompatActivity {


//    private String[] games = new String[]{"Space punks","Hello infinite","Genshin impact","PUBG","Dark night","cross out","rouge company","rocket league",
//            "amor valor","shadow arena"};
    @BindView(R.id.listView)
    ListView mListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);
        ButterKnife.bind(this);


//        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, games);
//        mListView.setAdapter(adapter);

        GameApi gameApi = GameClient.getClient();
        Call<GamesListResponse> call = gameApi.getGames();
        call.enqueue(new Callback<GamesListResponse>() {
            @Override
            public void onResponse(Call<GamesListResponse> call, Response<GamesListResponse> response) {
                if(response.isSuccessful()){
                    String gamesTitle = response.body().getTitle();
                    ArrayAdapter arrayAdapter = new GamesArrayAdapter(GamesActivity.this, android.R.layout.simple_list_item_1);
                }
            }

            @Override
            public void onFailure(Call<GamesListResponse> call, Throwable t) {

            }
        });
    }

}