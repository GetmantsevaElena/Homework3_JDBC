package com.academy.getmantseva.jdbc.model;

import java.util.Objects;

public class Transaction {
    private int transactinId;
    private int accountId;
    private int amount;

    public Transaction(int transactinId, int accountId, int amount) {
        this.transactinId = transactinId;
        this.accountId = accountId;
        this.amount = amount;
    }

    public int getTransactinId() {
        return transactinId;
    }

    public void setTransactinId(int transactinId) {
        this.transactinId = transactinId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction)) return false;
        Transaction that = (Transaction) o;
        return getTransactinId() == that.getTransactinId() && getAccountId() == that.getAccountId() && getAmount() == that.getAmount();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTransactinId(), getAccountId(), getAmount());
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactinId=" + transactinId +
                ", accountId=" + accountId +
                ", amount=" + amount +
                '}';
    }
}
