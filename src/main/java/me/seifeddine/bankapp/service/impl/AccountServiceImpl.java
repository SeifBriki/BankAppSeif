package me.seifeddine.bankapp.service.impl;

import me.seifeddine.bankapp.service.AccountService;
import me.seifeddine.bankapp.domaine.Account;
import me.seifeddine.bankapp.exception.BadRequestFoundException;
import me.seifeddine.bankapp.exception.NotFoundException;

import java.util.*;

public class AccountServiceImpl implements AccountService {

    private final Map<Long, Account> accounts = new HashMap<>();
    public long lastID = 1;

    @Override
    public List<Account> findAll() {
        return new ArrayList<>(accounts.values());
    }

    @Override
    public Account findById(long id) {
        return Optional
                .ofNullable(accounts.get(id))
                .orElseThrow(() -> new NotFoundException("ACCOUNT_NOT_FOUND"));
    }


    @Override
    public Account create(Account account) {
        if (account == null) throw new BadRequestFoundException("ACCOUNT_SHOULD_NOT_NULL");
        account.setId(lastID);
        account.setUser(null);
        lastID++;
        accounts.put(account.getId(), account);
        return account;
    }

    @Override
    public Account update(Long id, Account account) {
        if (account == null) throw new BadRequestFoundException("ACCOUNT_SHOULD_NOT_NULL");
        if (accounts.get(id) == null) throw new BadRequestFoundException("ACCOUNT_NOT_EXIST");
        account.setId(id);
        accounts.put(account.getId(), account);
        return account;
    }

    @Override
    public void linkAccountToUser(long id, long userID) {
        Account account = this.findById(id);
        account.setUser(userID);
        this.update(id, account);
    }

    @Override
    public void deleteById(long id) {
        this.accounts.remove(id);
    }

    @Override
    public void deposit(long id, double amount) {
        Optional.ofNullable(this.findById(id))
                .ifPresent(account -> {
                    account.deposit(amount);
                });

    }

    @Override
    public void withdraw(long id, double amount) {
        Optional.ofNullable(this.findById(id))
                .ifPresent(account -> {
                    account.withdraw(amount);
                });
    }

    @Override
    public Double getWealth(long user) {
        return this.accounts.values()
                .stream()
                .filter(account -> account != null && account.getUser() != null && account.getUser() == user)
                .filter(account -> account.getUser() == user)
                .mapToDouble(Account::getAccountBalance)
                .sum();
    }
}
