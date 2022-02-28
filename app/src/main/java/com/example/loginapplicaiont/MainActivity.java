package com.example.loginapplicaiont;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements LoginFragment.LoginFragmentLister, RegisterFragment.RegisterFragListener, AccountFragment.UpdateFragListener, UpdateAccountFragment.UpdateAccountListener {
    //implement this interface implements LoginFragment.LoginFragmentLister
    Button button;
    TextView tv;
    public static String EMAIL_KEY = "email";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        setTitle("Login");


        //Display Login Fragment
        getSupportFragmentManager().beginTransaction()
                .add(R.id.containerView, new LoginFragment())
                .commit();



    }
    DataServices.Account userAccount;

    @Override
    public void sendAccount(DataServices.Account account) {
        userAccount = account;

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerView, AccountFragment.newInstance(userAccount))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void goToRegister() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerView, new RegisterFragment())
                .addToBackStack(null)
                .commit();
    }


    @Override
    public void sendRegisteredAccount(DataServices.Account account) {
        userAccount = account;

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerView, AccountFragment.newInstance(userAccount))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onCancel() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerView, new LoginFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void sendToUpdate(DataServices.Account account) {
        userAccount = account;

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerView, UpdateAccountFragment.newInstance(userAccount))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void updateAcc(DataServices.Account account) {
        userAccount = account;

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerView, AccountFragment.newInstance(userAccount))
                .addToBackStack(null)
                .commit();
    }
}


