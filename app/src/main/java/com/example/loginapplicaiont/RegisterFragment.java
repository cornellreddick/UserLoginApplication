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

public class RegisterFragment extends Fragment {
Button regCancelButton, regSubmitButton;
EditText regEmail, regPassword, regName;

   // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public RegisterFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_register, container, false);
        //On cancel display login
        regCancelButton = v.findViewById(R.id.regCancelButton);
        regCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                registerFragListener.onCancel();
           }
        });

        regName = v.findViewById(R.id.regName);
        regName.getText().toString();

        regEmail = v.findViewById(R.id.regEmail);
        regEmail.getText().toString();

        regPassword = v.findViewById(R.id.regPassword);
        regPassword.getText().toString();
        regSubmitButton = v.findViewById(R.id.regSubmitButton);
        regSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = regName.getText().toString();
                String email = regEmail.getText().toString();
                String password = regPassword.getText().toString();

                if(name.isEmpty()){
                    Toast.makeText(getContext(), "Please enter a name!!", Toast.LENGTH_SHORT).show();
                } else if(email.isEmpty()) {
                    Toast.makeText(getContext(), "Please enter a email!!", Toast.LENGTH_SHORT).show();
                } else if (password.isEmpty()){
                    Toast.makeText(getContext(), "Please enter a password!!", Toast.LENGTH_SHORT).show();
                } else{
                    DataServices.AccountRequestTask task = DataServices.register(name, email, password);
                    if (task.isSuccessful()){
                        Toast.makeText(getContext(), "Registration Sucessful!!", Toast.LENGTH_SHORT).show();
                        DataServices.Account account = task.getAccount();
                        registerFragListener.sendRegisteredAccount(account);
                    }else {
                        Toast.makeText(getContext(), "Registration was not Sucessful!!", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        return v;
    }

    RegisterFragListener registerFragListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        registerFragListener = (RegisterFragListener) context;
    }

    interface RegisterFragListener{
        void sendRegisteredAccount(DataServices.Account account);
        void onCancel();
    }
}