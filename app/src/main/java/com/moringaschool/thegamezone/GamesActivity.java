package com.moringaschool.thegamezone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.EventLogTags;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

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



    @BindView(R.id.recyclerView)
    RecyclerView mrecyclerView;
    @BindView(R.id.floating)
    FloatingActionButton floating;
    List<GamesListResponse> gamesListResponse  = new ArrayList<>();
    GamesAdapter gamesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.purple_200)));
        ButterKnife.bind(this);
        gamesAdapter = new GamesAdapter(this, gamesListResponse);
//        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, games);
//        mListView.setAdapter(adapter);
        floating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                setRequestedOrientation(GamesActivity.SCREEN_ORIENTATION_LANSCAPE);
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            }
        });


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


        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search game");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String name) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String name) {
                gamesAdapter.getFilter().filter(name);
//                filterList(name);
                return false;
            }


        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
    private void filterList(String newText) {
//        List<GamesListResponse> gamesListResponses = new List <GamesListResponse>;

    }
}