package me.seifeddine.bankapp.service;

import me.seifeddine.bankapp.service.impl.AccountServiceImpl;
import me.seifeddine.bankapp.service.impl.UserServiceImpl;
import me.seifeddine.bankapp.domaine.Account;
import me.seifeddine.bankapp.domaine.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AccountServiceTest {
    private static final Account ACCOUNT1 = new Account( 10.0, LocalDate.now(), LocalDate.now());
    private static final Account ACCOUNT2 = new Account( 20.0, LocalDate.now().minusMonths(1), LocalDate.now());
    private static final Account ACCOUNT3 = new Account(30.0, LocalDate.now().minusMonths(2), LocalDate.now());
    private static final Account ACCOUNT4 = new Account(40.0, LocalDate.now().minusMonths(2), LocalDate.now());
    private static final User USERACCOUNT = new User("FN1", "LN1", 11, 11111111, "A1");
    private AccountServiceImpl accountService;
    private UserServiceImpl userService;

    @Before
    public void beforeMethod() {
        userService = new UserServiceImpl();
        userService.create(USERACCOUNT);
        accountService = new AccountServiceImpl();
        accountService.create(ACCOUNT1);
        accountService.create(ACCOUNT2);
        accountService.create(ACCOUNT3);
    }

    @After

    public void afterMethod () {
        userService = new UserServiceImpl();
        List<User> users = userService.findAll();
        accountService = new AccountServiceImpl();
        List<Account> accounts = accountService.findAll();
        if (accounts != null && accounts.isEmpty())
        {
            accountService = new AccountServiceImpl();
        }
        if (users != null && users.isEmpty())
        {
            userService = new UserServiceImpl();
        }
    }


    @Test
    public void findAllAccountsTest() {
        assertEquals(3, accountService.findAll().size());
    }

    @Test
    public void createAccountTest() {
        assertEquals(Long.valueOf(2), ACCOUNT2.getId());
        boolean compare = LocalDate.now().minusMonths(1).isEqual(ACCOUNT2.getCreateDateTime());
        assertTrue(compare);
        assertEquals(3, accountService.findAll().size());
    }

    @Test
    public void updateAccountTest() {
        Account updateAccount = accountService.update(1L, ACCOUNT3);
        boolean compare = LocalDate.now().minusMonths(2).isEqual(updateAccount.getCreateDateTime());
        assertTrue(compare);
    }

    @Test
    public void deleteAccountTest() {
        assertEquals(3, accountService.findAll().size());
        accountService.deleteById(1);
        assertEquals(2, accountService.findAll().size());
    }

    @Test
    public void linkAccountToUserTest () {
        accountService.linkAccountToUser(USERACCOUNT.getId(),ACCOUNT1.getId());
        assertEquals(USERACCOUNT.getId(), ACCOUNT1.getId());


    }

    @Test
    public void depositInAccountTest() {
        accountService.deposit(ACCOUNT1.getId(), 10);
        assertEquals(20, ACCOUNT1.getAccountBalance(), 0.001);
    }

    @Test
    public void withdrawFromAccountTest() {
        accountService.withdraw(ACCOUNT2.getId(), 10.5);
        assertEquals(9.5, ACCOUNT2.getAccountBalance(), 0.001);
    }

    @Test
    public void getUserWealthTest() {
        double wealth = accountService.getWealth(2);
        assertEquals(0, wealth, 0.001);

    }

}
