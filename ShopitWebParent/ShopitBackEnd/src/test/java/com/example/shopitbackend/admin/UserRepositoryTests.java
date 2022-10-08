package com.example.shopitbackend.admin;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.common.entity.Role;
import com.example.common.entity.User;
import com.example.shopitbackend.admin.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateNewUserWithOneRole() {
        Role roleAdmin = entityManager.find(Role.class, 1);
        User testUser = new User("user@abv.bg", "1234", "User", "Test");
        testUser.addRole(roleAdmin);

        User savedUser = userRepository.save(testUser);
        assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testCreateNewUserWithTwoRoles() {
        User testUserWithTwoRoles = new User("test@abv.bg", "121212", "Test", "Test");
        Role roleEditor = new Role(3);
        Role roleAssistant = new Role(5);

        testUserWithTwoRoles.addRole(roleEditor);
        testUserWithTwoRoles.addRole(roleAssistant);

        User savedUser = userRepository.save(testUserWithTwoRoles);
        assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testListAllUsers() {
        Iterable<User> listUsers = userRepository.findAll();
        listUsers.forEach(System.out::println);
    }

    @Test
    public void testGetUserById() {
        User user = userRepository.findById(1).get();
        System.out.println(user);
        assertThat(user).isNotNull();
    }

    @Test
    public void testUpdateUserDetails() {
        User user = userRepository.findById(1).get();
        user.setEnabled(true);
        user.setEmail("useruser123@abv.bg");

        userRepository.save(user);
    }

    @Test
    public void testUpdateUserRoles() {
        User user = userRepository.findById(2).get();
        Role roleEditor = new Role(3);
        Role roleSalesPerson = new Role(2);

        user.getRoles().remove(roleEditor);
        user.addRole(roleSalesPerson);

        userRepository.save(user);
    }

    @Test
    public void testDeleteUser() {
        Integer userId = 2;
        userRepository.deleteById(2);
    }

    @Test
    public void testGetUserByEmail() {
        String email = "pesho@abv.bg";
        User user = userRepository.getUserByEmail(email);

        assertThat(user).isNotNull();
    }
}
