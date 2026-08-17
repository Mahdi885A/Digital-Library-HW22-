
import ir.maktabsharif.enums.StockStatus;
import ir.maktabsharif.model.Book;
import ir.maktabsharif.model.PublisherAddress;
import ir.maktabsharif.repository.book.BookRepository;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BookRepositoryTest {

    private final BookRepository bookRepository = new BookRepository();


    @Test
    void testSaveAndFindBook() {

        PublisherAddress address = new PublisherAddress();

        address.setCity("Tehran");
        address.setStreet("Valiasr Street");
        address.setPostalCode("1234567890");

        Book book = new Book.Builder()
                .title("Clean Code")
                .isbn("9780132350884")
                .publicationYear(2008)
                .price(new BigDecimal("500000"))
                .stockStatus(StockStatus.IN_STOCK)
                .publisherAddress(address)
                .build();

        // Save
        Long id = bookRepository.save(book);

        // Find
        Optional<Book> result =
                bookRepository.findById(id);

        // Book exists
        assertTrue(result.isPresent());

        Book foundBook = result.get();

        // Verify title
        assertEquals(
                book.getTitle(),
                foundBook.getTitle()
        );

        // Verify PublisherAddress
        assertNotNull(foundBook.getPublisherAddress());

        assertEquals(
                "Tehran",
                foundBook.getPublisherAddress().getCity()
        );

        assertEquals(
                "Valiasr Street",
                foundBook.getPublisherAddress().getStreet()
        );

        assertEquals(
                "1234567890",
                foundBook.getPublisherAddress().getPostalCode()
        );
    }
}