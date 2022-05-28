package com.moringaschool.thegamezone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.signupbutton)
    Button mfindSignupButton;
    @BindView(R.id.username)
    EditText meditText;
    @BindView(R.id.location)
    EditText mLocationEditText;
    @BindView(R.id.agefield)
    EditText mageEditText;
    @BindView(R.id.preference)
    EditText mpreferenceEditText;
    @BindView(R.id.email)
    EditText memailEditText;
    @BindView(R.id.password)
    EditText mpasswordEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }
}