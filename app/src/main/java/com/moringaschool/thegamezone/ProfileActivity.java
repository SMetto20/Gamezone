package com.moringaschool.thegamezone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileActivity extends AppCompatActivity {
    @BindView(R.id.realnameTextView)
    TextView mrealnameTextView;
    @BindView(R.id.realageTextView)TextView mrealageTextView;
    @BindView(R.id.reallocTextView) TextView mreallocTextView;
    @BindView(R.id.realprefTextView) TextView mrealprefTextView;
    @BindView(R.id.viewbutton)
    Button mviewButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        mrealnameTextView.setText(username);
        String age = intent.getStringExtra("age");
        mrealageTextView.setText(age);
        String loc = intent.getStringExtra("location");
        mreallocTextView.setText(loc);
        String pref = intent.getStringExtra("preference");
        mrealprefTextView.setText(pref);
        mviewButton.setOnClickListener(new View.OnClickListener() { public void onClick(View v) {
            Intent intent = new Intent(ProfileActivity.this, GamesActivity.class);
            startActivity(intent);
        }
        });
    }
}