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
import android.widget.TextView;
import android.widget.Toast;
public class UpdateAccountFragment extends Fragment {
    EditText updateAcctName, updateAcctPassword;
    TextView updateAcctEmail;
    Button updateAcctSubmitBtn, updateAcctCancelBtn;

    private static final String ARG_PARAM_UPDATEACCOUNT = "param1";
    private DataServices.Account userAccount;
//    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;

    public UpdateAccountFragment() {
        // Required empty public constructor
    }

    public static UpdateAccountFragment newInstance(DataServices.Account userAccount) {
        UpdateAccountFragment fragment = new UpdateAccountFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM_UPDATEACCOUNT, userAccount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userAccount = (DataServices.Account) getArguments().getSerializable(ARG_PARAM_UPDATEACCOUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_update_account, container, false);

        updateAcctName = v.findViewById(R.id.updatedAcctName);
        updateAcctEmail = v.findViewById(R.id.updatedAcctEmail);
        updateAcctPassword = v.findViewById(R.id.updatedAcctPassword);

        updateAcctName.setText(userAccount.getName());
        updateAcctEmail.setText(userAccount.getEmail());
        updateAcctPassword.setText(userAccount.getPassword());

        updateAcctSubmitBtn = v.findViewById(R.id.updateAcctSubmitButton);
        updateAcctSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String email = updateAcctName.getText().toString();
                String name = updateAcctName.getText().toString();
                String password = updateAcctPassword.getText().toString();

                if(name.isEmpty()){
                    Toast.makeText(getContext(), "Please enter a name!!", Toast.LENGTH_SHORT).show();
                } else if (password.isEmpty()){
                    Toast.makeText(getContext(), "Please enter a password!!", Toast.LENGTH_SHORT).show();
                } else{
                    DataServices.AccountRequestTask task = DataServices.update(userAccount, name, password);
                    if (task.isSuccessful()){
                        Toast.makeText(getContext(), "Registration Sucessful!!", Toast.LENGTH_SHORT).show();
                        DataServices.Account account = task.getAccount();
                        updateAccountListener.updateAcc(account);
                    }else {
                        Toast.makeText(getContext(), "Registration was not Sucessful!!", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        updateAcctCancelBtn = v.findViewById(R.id.updateAcctCancelButton);
        updateAcctCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.containerView, new LoginFragment(), null).commit();
            }
        });

        return v;
    }

    UpdateAccountListener updateAccountListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        updateAccountListener = (UpdateAccountListener) context;
    }

    interface UpdateAccountListener{
        void updateAcc(DataServices.Account account);
}

}