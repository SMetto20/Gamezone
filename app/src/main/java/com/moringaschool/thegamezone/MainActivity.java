package com.moringaschool.thegamezone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
    @BindView(R.id.phonenumber)
    EditText mphoneEditText;
    @BindView(R.id.email)
    EditText memailEditText;
    @BindView(R.id.password)
    EditText mpasswordEditText;

    @BindView(R.id.signup)
    TextView msignup;

//    @BindView(R.id.proceedbutton)
//    Button  mproceedButton;
    FirebaseDatabase database;
    DatabaseReference ref ;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();


        mfindSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validateEmail();
                validateLocation();
                validatAge();
                validateUsername();
                validatePreference();
                validatePassword();
                Toast.makeText(MainActivity.this, "Fill in required fields", Toast.LENGTH_LONG).show();
                if (validateEmail()&&validatePreference()&&validatAge()&&validateUsername()&&validateLocation() && validatePassword()== true) {
//
                        String username = meditText.getText().toString();
                        String location = mLocationEditText.getText().toString();
                        String age = mageEditText.getText().toString();
                        String phonenumber = mphoneEditText.getText().toString();
                        String email = memailEditText.getText().toString();
                        String password = mpasswordEditText.getText().toString();

//                        Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
//                        intent.putExtra("username", username);
//                        intent.putExtra("location", location);
//                        intent.putExtra("age", age);
//                        intent.putExtra("phone", phonenumber);
//                        intent.putExtra("email", email);
//                        intent.putExtra("password", password);
//
//                        startActivity(intent);

                        createUser();

                  if(v.getId()==R.id.signupbutton)
                   getSupportFragmentManager().beginTransaction().add(R.id.container, new signInFragment()).commit();
                   mfindSignupButton.setVisibility(View.GONE);
                   meditText.setVisibility(View.GONE);
                   mageEditText.setVisibility(View.GONE);
                   memailEditText.setVisibility(View.GONE);
                   mphoneEditText.setVisibility(View.GONE);
                   mpasswordEditText.setVisibility(View.GONE);
                   mLocationEditText.setVisibility(View.GONE);
                   msignup.setVisibility(View.GONE);
                   database= FirebaseDatabase.getInstance();
                   ref = database.getReference("Users");
                   User user = new User(username,location,phonenumber,email,password,age);
                  ref.child(username).setValue(user);

                }

            }


        });
//        mproceedButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//
//            }
//        });
        }


    private boolean validateEmail() {
        String email = memailEditText.getText().toString();
        if (email.isEmpty()) {
            memailEditText.setError("field cannot be empty");
            return false;
        }  else {
            memailEditText.setError(null);
            return true;
        }
    }
    private boolean validateUsername() {
        String username = meditText.getText().toString();
        if (username.isEmpty()) {
            meditText.setError("field cannot be empty");
            return false;
        }  else {
            memailEditText.setError(null);
            return true;
        }
    }
    private boolean validatAge() {
        String age = mageEditText.getText().toString();
        if (age.isEmpty()) {
            mageEditText.setError("field cannot be empty");
            return false;
        }  else {
            memailEditText.setError(null);
            return true;
        }
    }
    private boolean validateLocation() {
        String location = mLocationEditText.getText().toString();
        if (location.isEmpty()) {
            mLocationEditText.setError("field cannot be empty");
            return false;
        }  else {
            memailEditText.setError(null);
            return true;
        }
    }
    private boolean validatePreference() {
        String phone = mphoneEditText.getText().toString();
        if (phone.isEmpty()) {
            mphoneEditText.setError("field cannot be empty");
            return false;
        } else {
            mphoneEditText.setError(null);
            return true;
        }
    }
    private boolean validatePassword() {
        String password = mpasswordEditText.getText().toString();
        if (password.isEmpty()) {
            mpasswordEditText.setError("field cannot be empty");
            return false;
        } else {
            mpasswordEditText.setError(null);
            return true;
        }
    }
    @Override
    public void onStart(){
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
//        if(user == null){    getSupportFragmentManager().beginTransaction().add(R.id.container, new signInFragment()).commit();
//                   mfindSignupButton.setVisibility(View.GONE);
//                   meditText.setVisibility(View.GONE);
//                   mageEditText.setVisibility(View.GONE);
//                   memailEditText.setVisibility(View.GONE);
//                   mphoneEditText.setVisibility(View.GONE);
//                   mpasswordEditText.setVisibility(View.GONE);
//                   mLocationEditText.setVisibility(View.GONE);
//                   msignup.setVisibility(View.GONE);
//
//        }
    }
    private void createUser(){
        String email = memailEditText.getText().toString();
        String password = mpasswordEditText.getText().toString();
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "user registration succesful", Toast.LENGTH_SHORT).show();
                    getSupportFragmentManager().beginTransaction().add(R.id.container, new signInFragment()).commit();
                    mfindSignupButton.setVisibility(View.GONE);
                    meditText.setVisibility(View.GONE);
                    mageEditText.setVisibility(View.GONE);
                    memailEditText.setVisibility(View.GONE);
                    mphoneEditText.setVisibility(View.GONE);
                    mpasswordEditText.setVisibility(View.GONE);
                    mLocationEditText.setVisibility(View.GONE);
                    msignup.setVisibility(View.GONE);
                }else{
                    Toast.makeText(MainActivity.this, "Registration Error:" + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

    }


}




