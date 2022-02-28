package com.example.loginapplicaiont;

import java.io.Serializable;
import java.util.HashMap;

public class DataServices implements Serializable{
    private static HashMap<String, Account> accounts = new HashMap<String, Account>(){{
        put("a@a.com", new Account("Alice Smith", "a@a.com", "test123"));
        put("b@b.com", new Account("Bob Smith", "b@b.com", "test123"));
        put("c@c.com", new Account("Charles Smith", "c@c.com", "test123"));
    }};

    public static AccountRequestTask login(String email, String password){

        if(email == null || email.isEmpty() ){
            return new AccountRequestTask("Enter valid email!");
        }

        if(!accounts.containsKey(email.trim().toLowerCase())){
            return new AccountRequestTask("No user with this email!");
        }

        Account account = accounts.get(email.trim().toLowerCase());

        if(account == null || !account.getPassword().equals(password)){
            return new AccountRequestTask("The provided email/password do not match!");
        }

        return new AccountRequestTask(account);
    }

    public static AccountRequestTask register(String name, String email, String password){

        if(name == null || name.isEmpty() ){
            return new AccountRequestTask("Enter valid name!");
        }

        if(email == null || email.isEmpty() ){
            return new AccountRequestTask("Enter valid email!");
        }

        if(password == null || password.isEmpty() ){
            return new AccountRequestTask("Enter valid password!");
        }

        if(accounts.containsKey(email.trim().toLowerCase())){
            return new AccountRequestTask("Email provided already taken by another account. Choose another email!");
        }

        Account account = new Account(name, email.trim().toLowerCase(), password);
        accounts.put(email.trim().toLowerCase(), account);
        return new AccountRequestTask(account);
    }

    public static AccountRequestTask update(Account oldAccount, String name, String password){
        if(oldAccount == null){
            return new AccountRequestTask("Enter valid account !!");
        }

        if(name == null || name.isEmpty() ){
            return new AccountRequestTask("Enter valid name!");
        }

        if(password == null || password.isEmpty() ){
            return new AccountRequestTask("Enter valid password!");
        }

        if(oldAccount.getEmail() == null || oldAccount.getEmail().isEmpty() ){
            return new AccountRequestTask("Enter valid email!");
        }

        if(!accounts.containsKey(oldAccount.getEmail().trim().toLowerCase())){
            return new AccountRequestTask("Provided account is invalid!");
        }

        String email = oldAccount.getEmail().trim().toLowerCase();

        Account account = new Account(name,email,password);
        accounts.put(email, account);

        return new AccountRequestTask(account);
    }

    public static class Account implements Serializable {
        private String name, email, password;
        public Account(String name, String email, String password) {
            this.name = name;
            this.email = email;
            this.password = password;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }
    }

    public static class AccountRequestTask {
        private boolean isSuccessful;
        private String errorMessage;
        private Account account;

        public AccountRequestTask(String error){
            this.isSuccessful = false;
            this.errorMessage = error;
            this.account = null;
        }

        public AccountRequestTask(Account account){
            this.isSuccessful = true;
            this.errorMessage = null;
            this.account = account;
        }

        public boolean isSuccessful() {
            return isSuccessful;
        }


        public String getErrorMessage() {
            return errorMessage;
        }


        public Account getAccount() {
            return account;
        }
    }
}
