
import ir.maktabsharif.enums.StockStatus;
import ir.maktabsharif.model.Book;
import ir.maktabsharif.model.Category;
import ir.maktabsharif.repository.book.BookRepository;
import ir.maktabsharif.repository.category.CategoryRepository;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CategoryRepositoryTest {

    private final CategoryRepository categoryRepository =
            new CategoryRepository();

    private final BookRepository bookRepository =
            new BookRepository();


    @Test
    void testCategoryCascadePersist() {

        // Create Category
        Category category = new Category();
        category.setName("Programming");
        category.setBook(new ArrayList<>());


        // Create Book 1
        Book book1 = new Book.Builder()
                .title("Clean Code")
                .isbn("ISBN-TEST-001")
                .publicationYear(2008)
                .price(new BigDecimal("500000"))
                .stockStatus(StockStatus.IN_STOCK)
                .category(category)
                .build();


        // Create Book 2
        Book book2 = new Book.Builder()
                .title("Effective Java")
                .isbn("ISBN-TEST-002")
                .publicationYear(2018)
                .price(new BigDecimal("600000"))
                .stockStatus(StockStatus.IN_STOCK)
                .category(category)
                .build();


        // Add books to category
        category.getBook().add(book1);
        category.getBook().add(book2);


        // Save ONLY Category
        Long categoryId =
                categoryRepository.save(category);


        // Find Category
        Optional<Category> result =
                categoryRepository.findById(categoryId);


        // Category must exist
        assertTrue(result.isPresent());

        Category savedCategory = result.get();


        // Category must contain the books
        assertNotNull(savedCategory.getBook());

        assertEquals(
                2,
                savedCategory.getBook().size()
        );


        // Verify that books received IDs
        assertNotNull(book1.getId());
        assertNotNull(book2.getId());


        // Verify Book 1 was persisted
        Optional<Book> book1Result =
                bookRepository.findById(book1.getId());

        assertTrue(book1Result.isPresent());

        Book savedBook1 = book1Result.get();

        assertEquals(
                "Clean Code",
                savedBook1.getTitle()
        );


        // Verify Book 2 was persisted
        Optional<Book> book2Result =
                bookRepository.findById(book2.getId());

        assertTrue(book2Result.isPresent());

        Book savedBook2 = book2Result.get();

        assertEquals(
                "Effective Java",
                savedBook2.getTitle()
        );
    }
}