package com.example.hmg.wishlistBE;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.hmg.wishlistBE.entity.Follows;
import com.example.hmg.wishlistBE.repository.FollowsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(true)
public class FollowsRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private FollowsRepository repo;

    @Test
    public void testCreateUser() {
        Follows follows = new Follows("test1", "test2");

        Follows savedFollows = repo.save(follows);

        Follows existFollows = entityManager.find(Follows.class, savedFollows.getId());

        assertThat(follows.getId()).isEqualTo(existFollows.getId());

    }
}