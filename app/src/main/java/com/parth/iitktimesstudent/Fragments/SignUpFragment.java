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


public class SignUpFragment extends Fragment {
    private EditText register_username,register_email,register_password,register_confirm_password;
    private Button btn_register;
    private FirebaseAuth firebaseAuth;
    private FrameLayout parentFrameLayout;
    private TextView already_have_an_account;
    private ProgressBar progressBar;
    public SignUpFragment() {
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterUser();
            }
        });
        already_have_an_account.setOnClickListener(new View.OnClickListener() {
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
        View view= inflater.inflate(R.layout.fragment_sign_up, container, false);
        register_email = view.findViewById(R.id.register_email);
        register_username = view.findViewById(R.id.register_username);
        register_password = view.findViewById(R.id.register_password);
        register_confirm_password = view.findViewById(R.id.register_confirm_password);
        btn_register =view.findViewById(R.id.btn_register);
        firebaseAuth = FirebaseAuth.getInstance();
        parentFrameLayout =getActivity().findViewById(R.id.register_frameLayout);
        already_have_an_account = view.findViewById(R.id.tv_login);
        progressBar = view.findViewById(R.id.register_progressBar);
        return view;
    }

    private void RegisterUser(){
        String email = register_email.getText().toString();
        String password = register_password.getText().toString();
        String confirm_password = register_confirm_password.getText().toString();
        if(!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            if(!TextUtils.isEmpty(password)&& password.length()>=8){
                if(confirm_password.equals(password)){
                    progressBar.setVisibility(View.VISIBLE);
                    btn_register.setEnabled(false);
                    firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(getActivity(),"Registered Successfully",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getActivity(), MainActivity.class);
                                startActivity(intent);
                                btn_register.setEnabled(true);
                                getActivity().finish();
                            }else{
                                progressBar.setVisibility(View.INVISIBLE);
                                btn_register.setEnabled(true);
                                String error =task.getException().getMessage();
                                Toast.makeText(getActivity(),error,Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else{
                    register_confirm_password.setError("password does not matches");
                }
            }else{
                register_password.setError("please choose atleast 8 characters");
            }
        }else{
            register_email.setError("invalid email");
        }
    }

    private void setFragment(){
        getActivity().getSupportFragmentManager().beginTransaction().replace(parentFrameLayout.getId(),new SignInFragment()).commit();
    }
}