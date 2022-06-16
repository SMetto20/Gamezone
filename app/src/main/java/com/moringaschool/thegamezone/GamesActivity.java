package com.moringaschool.thegamezone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.EventLogTags;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import adapters.GamesAdapter;
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
    @BindView(R.id.recyclerView)
    RecyclerView mrecyclerView;
//    @BindView(R.id.listview)
//    ListView mListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.purple_200)));
        ButterKnife.bind(this);

//        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, games);
//        mListView.setAdapter(adapter);



        GameApi gameApi = GameClient.getClient();
        Call <List<GamesListResponse>> call = gameApi.getGames();
        call.enqueue(new Callback<List<GamesListResponse>>() {
            @Override
            public void onResponse(Call <List<GamesListResponse>> call, Response<List<GamesListResponse>> response) {
                if(response.isSuccessful()){
                  List<GamesListResponse> gamesListResponse =  response.body();
                    GamesAdapter gamesAdapter = new GamesAdapter(GamesActivity.this, gamesListResponse);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(GamesActivity.this,LinearLayoutManager.VERTICAL,false);
                    mrecyclerView.setLayoutManager(linearLayoutManager);
                    mrecyclerView.setAdapter(gamesAdapter);
                    Toast.makeText(GamesActivity.this, "succesful", Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(GamesActivity.this, "not successful", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<GamesListResponse>> call, Throwable t) {
                Log.e("GamesActivity","on failure",t);
                Toast.makeText(GamesActivity.this, t.toString(), Toast.LENGTH_LONG).show();

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        ButterKnife.bind(this);

//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mEditor = mSharedPreferences.edit();

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search game");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}