package com.moringaschool.thegamezone;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignInActivity extends AppCompatActivity {
    @BindView(R.id.email2)
    EditText mEmail2;

    @BindView(R.id.password2)
    EditText mPassword2;

    @BindView(R.id.username2)
    EditText mUsername2;

    @BindView(R.id.proceedbutton)
    Button mproceedbutton;


    FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
//        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.purple_200)));
        ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        };

        mproceedbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == mproceedbutton) {
                    loginUser();
//                    showProgressBar();

                }

            }
        });
    }

    private void loginUser(){
//        String email = memailEditText.getText().toString();
//        String password = mpasswordEditText.getText().toString();
        String password2 = mPassword2.getText().toString();
        String email2 = mEmail2.getText().toString();
        String username2 = mUsername2.getText().toString();
//        String username = mName.getText().toString();
//        String location = mLocation.getText().toString();
//        String age = mAge.getText().toString();
//        String phonenumber = mPhone.getText().toString();
        if (email2.isEmpty()) {
            mEmail2.setError("field cannot be empty");
            mEmail2.requestFocus();
        }  else if(password2.isEmpty()) {
            mPassword2.setError("field cannot be empty");
            mPassword2.requestFocus();
        }else{
            mAuth.signInWithEmailAndPassword(email2, password2)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());
                            if (!task.isSuccessful()) {
                                Log.w(TAG, "signInWithEmail", task.getException());
                                Toast.makeText(SignInActivity.this, "Authentication passed.",
                                        Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignInActivity.this, ProfileActivity.class);
                                startActivity(intent);
                            }
                        }
//
//
////                        Intent intent = new Intent(getActivity(), ProfileActivity.class);
////                        intent.putExtra("username", username);
////                        intent.putExtra("location", location);
////                        intent.putExtra("age", age);
////                        intent.putExtra("phone", phonenumber);
////                        intent.putExtra("email", email);
////                        intent.putExtra("password", password);
////
////
        });

        }
    }
//    @Override
//    public void onStart() {
//        super.onStart();
//        mAuth.addAuthStateListener(mAuthListener);
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        if (mAuthListener != null) {
//            mAuth.removeAuthStateListener(mAuthListener);
//        }
//    }
}