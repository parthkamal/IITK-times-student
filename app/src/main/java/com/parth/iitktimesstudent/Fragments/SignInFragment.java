package com.parth.iitktimesstudent.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.parth.iitktimesstudent.MainActivity;
import com.parth.iitktimesstudent.R;

import org.jetbrains.annotations.NotNull;


public class SignInFragment extends Fragment {
    private EditText login_email,login_password;
    private Button btn_login;
    private TextView dont_have_an_account;
    private FrameLayout parentFrameLayout;
    private FirebaseAuth firebaseAuth;
    private ProgressBar progressBar;


    public SignInFragment() {
        // Required empty public constructor
    }


    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });
        dont_have_an_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        login_email = view.findViewById(R.id.login_email);
        login_password = view.findViewById(R.id.login_password);
        btn_login = view.findViewById(R.id.btn_login);
        dont_have_an_account = view.findViewById(R.id.tv_register);
        progressBar = view.findViewById(R.id.login_progressBar);
        parentFrameLayout = getActivity().findViewById(R.id.register_frameLayout);
        firebaseAuth = FirebaseAuth.getInstance();

        return view;
    }

    private void setFragment(){
        getActivity().getSupportFragmentManager().beginTransaction().replace(parentFrameLayout.getId(),new SignUpFragment()).commit();
    }
    private void loginUser(){
        String email = login_email.getText().toString();
        String password = login_password.getText().toString();
        if(!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            if(!TextUtils.isEmpty(password)&& password.length()>=8){
                btn_login.setEnabled(false);
                progressBar.setVisibility(View.VISIBLE);
                    firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(getActivity(),"logged-in Successfully",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getActivity(), MainActivity.class);
                                startActivity(intent);
                                btn_login.setEnabled(true);
                                getActivity().finish();
                            }else{
                                btn_login.setEnabled(true);
                                progressBar.setVisibility(View.INVISIBLE);
                                String error =task.getException().getMessage();
                                Toast.makeText(getActivity(),error,Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else{ login_password.setError("password should have atleast 8 charaters"); }
        }else{ login_email.setError("invalid email"); }
    }
}