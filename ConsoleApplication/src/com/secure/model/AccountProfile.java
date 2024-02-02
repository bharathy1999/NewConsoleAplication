package com.secure.model;

public class AccountProfile {
    private String username;
    private String password;
    private Long accountNumber;
   

    public AccountProfile(String username, String password,Long accountNumber) {
        this.username = username;
        this.password = password;
        this.accountNumber=accountNumber;
        
    }
    public AccountProfile(String username,Long accountNumber) {
        this.username = username;
        this.accountNumber=accountNumber;
        
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }
}
