package com.moringaschool.thegamezone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
        mfindSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = meditText.getText().toString();
                String location = mLocationEditText.getText().toString();
                String age = mageEditText.getText().toString();
                String preference = mpreferenceEditText.getText().toString();
                String email = memailEditText.getText().toString();
                String password = mpasswordEditText.getText().toString();

                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                intent.putExtra("username", username);
                intent.putExtra("location", location);
                intent.putExtra("age", age);
                intent.putExtra("preference", preference);
                intent.putExtra("email", email);
                intent.putExtra("password", password);

                startActivity(intent);
            }


        });






    }
}