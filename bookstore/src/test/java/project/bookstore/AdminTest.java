package project.bookstore;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;
import project.bookstore.entity.user.User;
import project.bookstore.enums.Role;
import project.bookstore.repository.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class AdminTest {
    @Autowired
    private UserRepository repository;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testAddAdmin() {
        User admin = new User();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode("admin");

        admin.setFirstName("Admin");
        admin.setLastName("Bookland");
        admin.setEmail("admin@gmail.com");
        admin.setPassword(encodedPassword);
        admin.setRole(Role.ADMIN);

        User savedUser = repository.save(admin);
        User existUser = entityManager.find(User.class, savedUser.getId());

        Assertions.assertEquals(admin.getEmail(), existUser.getEmail());
    }
}