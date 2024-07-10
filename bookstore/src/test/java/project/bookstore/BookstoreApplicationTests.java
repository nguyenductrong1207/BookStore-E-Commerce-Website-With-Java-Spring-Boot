package project.bookstore;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import project.bookstore.entity.Category;

@SpringBootTest
class BookstoreApplicationTests {

	@Test
	void contextLoads() {
		Category category = new Category();
		category.setName("novel");
		category.setDescription("abc");
		Assertions.assertEquals("novel", category.getName());
	}

}
