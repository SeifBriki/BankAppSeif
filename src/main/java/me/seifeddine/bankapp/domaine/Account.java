package me.seifeddine.bankapp.domaine;

import java.time.LocalDate;

public class Account {
    private Long id;
    private Double accountBalance;
    private LocalDate createDateTime;
    private LocalDate updateDateTime;
    private Long user;

    public Account() {
    }

    public Account( Double accountBalance, LocalDate createDateTime, LocalDate updateDateTime) {
        this.accountBalance = accountBalance;
        this.createDateTime = createDateTime;
        this.updateDateTime = updateDateTime;
    }

    public Account( Double accountBalance, LocalDate createDateTime, LocalDate updateDateTime, Long user) {
        this.accountBalance = accountBalance;
        this.createDateTime = createDateTime;
        this.updateDateTime = updateDateTime;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public Double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public void deposit(double amount) {
        this.accountBalance = this.accountBalance + amount;
    }

    public void withdraw(double amount) {
        this.accountBalance = this.accountBalance - amount;
    }

    public LocalDate getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(LocalDate createDateTime) {
        this.createDateTime = createDateTime;
    }

    public LocalDate getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(LocalDate updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }
}
