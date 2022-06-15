package com.moringaschool.thegamezone;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;


public class signInFragment extends Fragment {
    EditText mEmail2;
    EditText mPassword2;
    EditText mUsername2;
    Button mproceedbutton;
    FirebaseAuth mAuth;
    EditText memailEditText;
    EditText mpasswordEditText;
    EditText mName;
    EditText mLocation;
    EditText mAge;
    EditText mPhone;



    public signInFragment() {
        // Required empty public constructor
    }

//    // TODO: Rename and change types and number of parameters
    public static signInFragment newInstance(String param1, String param2) {
        signInFragment fragment = new signInFragment();

        return fragment;
    }

//    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);

        mEmail2 = view.findViewById(R.id.email2);
        mPassword2 =  view.findViewById(R.id.password2);
        mUsername2 = view.findViewById(R.id.username2);
        mproceedbutton=  view.findViewById(R.id.proceedbutton);
        mAuth = FirebaseAuth.getInstance();
//        memailEditText = view.findViewById(R.id.email);
//        mpasswordEditText = view.findViewById(R.id.password);
//        mName = view.findViewById(R.id.username);
//        mAge = view.findViewById(R.id.age);
//        mLocation = view.findViewById(R.id.location);
//        mPhone = view.findViewById(R.id.phonenumber);
        mproceedbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();

            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mEmail2 = view.findViewById(R.id.email2);
        mPassword2 =  view.findViewById(R.id.password2);
        mproceedbutton=  view.findViewById(R.id.proceedbutton);
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
            Query checkEmail = FirebaseDatabase.getInstance().getReference("Users").orderByChild("username").equalTo(username2);
            checkEmail.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists());
                    String systemEmail = snapshot.child(username2).child("email").getValue(String.class);
                    String systemPass= snapshot.child(username2).child("password").getValue(String.class);
                    if (systemEmail.equals(email2) && systemPass.equals(password2)){

                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


//            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                @Override
//                public void onComplete(@NonNull Task<AuthResult> task) {
//                    if(task.isSuccessful()) {
////                        Toast.makeText(MainActivity.this, "Login is succesful", Toast.LENGTH_SHORT).show();
////                        Intent intent = new Intent(getActivity(), ProfileActivity.class);
////                        intent.putExtra("username", username);
////                        intent.putExtra("location", location);
////                        intent.putExtra("age", age);
////                        intent.putExtra("phone", phonenumber);
////                        intent.putExtra("email", email);
////                        intent.putExtra("password", password);
////
////                        startActivity(intent);
//                    }else{
////                        Toast.makeText(signInFragment.this, "Login Error:" + task.getException().getMessage(), Toast.LENGTH_LONG).show();
//                    }
//                }
//            });
        }

    }

}