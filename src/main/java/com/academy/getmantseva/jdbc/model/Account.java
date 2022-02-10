package com.academy.getmantseva.jdbc.model;


import java.util.Objects;

public class Account {
    private int accountId;
    private int userId;
    private float balance;
    private String currency;

    public Account() {
        this.accountId = accountId;
        this.userId = userId;
        this.balance = balance;
        this.currency = currency;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return accountId == account.accountId && userId == account.userId && Float.compare(account.balance, balance) == 0 && currency.equals(account.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, userId, balance, currency);
    }

}