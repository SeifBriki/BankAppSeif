package me.seifeddine.bankapp.service;

import me.seifeddine.bankapp.service.impl.UserServiceImpl;
import me.seifeddine.bankapp.domaine.Account;
import me.seifeddine.bankapp.domaine.User;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class UserServiceTest {

    private static final Account ACCOUNT1 = new Account( 10.0, LocalDate.now(), LocalDate.now());
    private static final Account ACCOUNT2 = new Account(-5.0, LocalDate.now().minusMonths(1), LocalDate.now());
    private static final Account ACCOUNT3 = new Account( 30.0, LocalDate.now().minusMonths(2), LocalDate.now());
    private static final User USER1 = new User("FN1", "LN1", 11, 11111111, "A1");
    private static final User USER2 = new User("FN2", "LN2", 22, 22222222, "A2");
    private static final User USER3 = new User("FN3", "LN3", 33, 33333333, "A3");
    List<Account> accountList = new ArrayList<>();
    private UserServiceImpl userService;

    @Before
    public void beforeMethod() {
        accountList.add(ACCOUNT1);
        accountList.add(ACCOUNT2);
        accountList.add(ACCOUNT3);
        userService = new UserServiceImpl();
        userService.create(USER1);
    }

    @Test
    public void findAllUsersTest() {
        assertEquals(1, userService.findAll().size());
    }

    @Test
    public void findUserByIdTest() {
        User user = userService.findById(1);
        assertEquals("FN1", user.getFirstName());
        assertEquals("LN1", user.getLastName());
        assertEquals(11, user.getAge());
        assertEquals(Integer.valueOf(11111111), user.getPhone());
        assertEquals("A1", user.getAddress());
    }

    @Test
    public void createUserTest() {
        User newUser = userService.create(USER2);
        assertEquals(Long.valueOf(2), newUser.getId());
        assertEquals("FN2", newUser.getFirstName());
        assertEquals(2, userService.findAll().size());
    }

    @Test
    public void updateUserTest() {
        User updateUser = userService.update(1, USER3);
        assertEquals("FN3", updateUser.getFirstName());
        assertEquals(1, userService.findAll().size());
    }

    @Test
    public void deleteUserTest() {
        assertEquals(1, userService.findAll().size());
        userService.deleteById(1);
        assertEquals(0, userService.findAll().size());
    }

}
