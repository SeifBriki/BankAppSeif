package me.seifeddine.bankapp.service;

import me.seifeddine.bankapp.domaine.Account;

import java.util.List;

public interface AccountService {

    List<Account> findAll();

    Account findById(long id);

    Account create(Account Account);

    Account update(Long accountNumber, Account account);

    void linkAccountToUser(long accountNumber, long userID);

    void deleteById(long accountNumber);

    void deposit(long accountNumber, double amount);

    void withdraw(long accountNumber, double amount);

    Double getWealth(long user);
}
