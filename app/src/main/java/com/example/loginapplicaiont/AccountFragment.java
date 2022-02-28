package com.example.loginapplicaiont;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AccountFragment extends Fragment {
    Button editAcctBtn, cancelAcctBtn ;
    TextView welcomeTv, nameTV;
    String data;

    private static final String ARG_PARAM_ACCOUNT = "param1";
    private DataServices.Account userAccount;
    private String usearEmail;
    public AccountFragment() {
        // Required empty public constructor
    }

    public static AccountFragment newInstance(DataServices.Account userAccount) {
        AccountFragment fragment = new AccountFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM_ACCOUNT, userAccount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userAccount = (DataServices.Account) getArguments().getSerializable(ARG_PARAM_ACCOUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_account, container, false);
        nameTV = v.findViewById(R.id.emailTv);
        nameTV.setText(userAccount.getName());

        editAcctBtn = v.findViewById(R.id.editAcctBtn);
        editAcctBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = userAccount.getName();
                String email = userAccount.getEmail();
                String password = userAccount.getPassword();

                if(name.isEmpty()){
                    Toast.makeText(getContext(), "Please enter a name!!", Toast.LENGTH_SHORT).show();
                } else if(email.isEmpty()) {
                    Toast.makeText(getContext(), "Please enter a email!!", Toast.LENGTH_SHORT).show();
                } else if (password.isEmpty()){
                    Toast.makeText(getContext(), "Please enter a password!!", Toast.LENGTH_SHORT).show();
                } else{
                    DataServices.AccountRequestTask task = DataServices.update(userAccount, name, password);
                    if (task.isSuccessful()){
                        Toast.makeText(getContext(), "Registration Sucessful!!", Toast.LENGTH_SHORT).show();
                        DataServices.Account account = task.getAccount();
                        updateFragListener.sendToUpdate(account);
                    }else {
                        Toast.makeText(getContext(), "Registration was not Sucessful!!", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });

        cancelAcctBtn = v.findViewById(R.id.acctLogoutBtn);
        cancelAcctBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.containerView, new LoginFragment(), null).commit();
            }
        });

        return v;
    }

    UpdateFragListener updateFragListener;

    @Override
    public  void onAttach(@NonNull Context context) {
        super.onAttach(context);
        updateFragListener = (UpdateFragListener) context;

    }

    interface UpdateFragListener{
        void sendToUpdate(DataServices.Account account);
    }
}