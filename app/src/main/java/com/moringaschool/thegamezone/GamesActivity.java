package com.moringaschool.thegamezone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GamesActivity extends AppCompatActivity {
    private String[] games = new String[]{"Space punks","Hello infinite","Genshin impact","PUBG","Dark night","cross out","rouge company","rocket league",
            "amor valor","shadow arena"};
    @BindView(R.id.listView)
    ListView mListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);
        ButterKnife.bind(this);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, games);
        mListView.setAdapter(adapter);
    }
}