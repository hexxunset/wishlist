package com.example.hmg.wishlistBE;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.hmg.wishlistBE.entity.Wish;
import com.example.hmg.wishlistBE.repository.WishRepository;
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
public class WishRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private WishRepository repo;

    @Test
    public void testCreateUser() {
        Wish wish = new Wish();
        wish.setPersonId(Long.valueOf(1));
        wish.setWishName("Ullteppe");

        Wish savedWish = repo.save(wish);

        Wish existWish = entityManager.find(Wish.class, savedWish.getId());

        assertThat(wish.getWishName()).isEqualTo(existWish.getWishName());

    }
}