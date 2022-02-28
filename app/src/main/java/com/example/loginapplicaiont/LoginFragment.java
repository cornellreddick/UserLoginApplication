package com.example.loginapplicaiont;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.StandardSocketOptions;


public class LoginFragment extends Fragment {
    Button loginButton, registerButton;
    EditText userIput, userPassword;



   // EditText emailLogin, password;
   public LoginFragment() {
       // Required empty public constructor
   }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_login, container, false);
        userIput = v.findViewById(R.id.emailInput);
        userPassword = v.findViewById(R.id.passwordInput);

           // Display Account Fragment
        loginButton = v.findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {

               @Override
               public void onClick(View view) {
                   String email = userIput.getText().toString();
                   String password = userPassword.getText().toString();

                   if (email.isEmpty()){
                        Toast.makeText(getActivity(), "Enter a valid email", Toast.LENGTH_SHORT).show();
                   }else if (password.isEmpty()){
                       Toast.makeText(getActivity(), "Enter a valid email", Toast.LENGTH_SHORT).show();
                   }else{
                       DataServices.AccountRequestTask task = DataServices.login(email, password);
                       if (task.isSuccessful()){
                            DataServices.Account account = task.getAccount();
                           mListerner.sendAccount(account);
                       }

                   }
               }
           });
        // Display Register Fragment
        registerButton = v.findViewById(R.id.createNewAccountButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListerner.goToRegister();
            }
        });

        return  v;
    }


    LoginFragmentLister mListerner;

    @Override
    public  void onAttach(@NonNull Context context){
        super.onAttach(context);
        mListerner = (LoginFragmentLister) context;
    }

    interface  LoginFragmentLister{
        void sendAccount(DataServices.Account account);
        void goToRegister();
    }
}