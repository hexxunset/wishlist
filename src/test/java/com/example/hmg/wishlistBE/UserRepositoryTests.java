package com.example.hmg.wishlistBE;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.hmg.wishlistBE.entity.User;
import com.example.hmg.wishlistBE.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false) // Uncomment if you want to store test data permanently
public class UserRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository repo;

    @Test
    void testCreateUser() {
        User user = new User();
        user.setUsername("helle");
        user.setPassword("myPass123!");
        user.setName("Helle");

        User savedUser = repo.save(user);

        User existUser = entityManager.find(User.class, savedUser.getId());

        assertThat(user.getUsername()).isEqualTo(existUser.getUsername());
    }
}