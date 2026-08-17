package ir.maktabsharif.app;

import ir.maktabsharif.enums.StockStatus;
import ir.maktabsharif.model.*;
import ir.maktabsharif.service.AuthorService;
import ir.maktabsharif.service.BookService;
import ir.maktabsharif.service.CategoryService;
import ir.maktabsharif.service.ProfileService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        BookService bookService = new BookService();
        AuthorService authorService = new AuthorService();
        ProfileService profileService = new ProfileService();
        CategoryService categoryService = new CategoryService();


        // =========================
        // Authors
        // =========================

        Author author1 = new Author.Builder()
                .name("Robert C. Martin")
                .birthDate(LocalDate.of(1952, 12, 5))
                .build();

        Author author2 = new Author.Builder()
                .name("Joshua Bloch")
                .birthDate(LocalDate.of(1961, 8, 28))
                .build();

        Author author3 = new Author.Builder()
                .name("Martin Fowler")
                .birthDate(LocalDate.of(1963, 12, 18))
                .build();


        // =========================
        // Profiles
        // =========================

        Profile profile1 = new Profile();
        profile1.setBio("Software engineer and author");
        profile1.setWebSite("https://example.com/robert");

        Profile profile2 = new Profile();
        profile2.setBio("Java expert and author");
        profile2.setWebSite("https://example.com/joshua");

        Profile profile3 = new Profile();
        profile3.setBio("Software developer and writer");
        profile3.setWebSite("https://example.com/martin");


        // ارتباط Author و Profile
        author1.setProfile(profile1);
        profile1.setAuthor(author1);

        author2.setProfile(profile2);
        profile2.setAuthor(author2);

        author3.setProfile(profile3);
        profile3.setAuthor(author3);


        // =========================
        // Save Authors
        // =========================

        Long author1Id = authorService.save(author1);
        Long author2Id = authorService.save(author2);
        Long author3Id = authorService.save(author3);


        // =========================
        // Categories
        // =========================

        Category programming =
                new Category();

        programming.setName("Programming");
        programming.setBook(new ArrayList<>());

        Category software = new Category();

        software.setName("Software Engineering");
        software.setBook(new ArrayList<>());


        // =========================
        // Publisher Addresses
        // =========================

        PublisherAddress address1 = new PublisherAddress();

        address1.setCity("New York");
        address1.setStreet("5th Avenue");
        address1.setPostalCode("10001");


        PublisherAddress address2 = new PublisherAddress();

        address2.setCity("Boston");
        address2.setStreet("Main Street");
        address2.setPostalCode("02108");


        PublisherAddress address3 = new PublisherAddress();

        address3.setCity("London");
        address3.setStreet("Oxford Street");
        address3.setPostalCode("W1D");


        // =========================
        // Books using Builder
        // =========================

        Book book1 = new Book.Builder()
                .title("Clean Code")
                .isbn("9780132350884")
                .publicationYear(2008)
                .price(new BigDecimal("500000"))
                .stockStatus(StockStatus.IN_STOCK)
                .publisherAddress(address1)
                .author(new ArrayList<>(List.of(author1)))
                .category(programming)
                .build();


        Book book2 = new Book.Builder()
                .title("Effective Java")
                .isbn("9780134685991")
                .publicationYear(2018)
                .price(new BigDecimal("650000"))
                .stockStatus(StockStatus.IN_STOCK)
                .publisherAddress(address2)
                .author(new ArrayList<>(List.of(author2)))
                .category(programming)
                .build();


        Book book3 = new Book.Builder()
                .title("Refactoring")
                .isbn("9780134757599")
                .publicationYear(2018)
                .price(new BigDecimal("700000"))
                .stockStatus(StockStatus.COMING_SOON)
                .publisherAddress(address3)
                .author(new ArrayList<>(List.of(author3)))
                .category(software)
                .build();


        // =========================
        // Category ↔ Book
        // =========================

        programming.getBook().add(book1);
        programming.getBook().add(book2);

        software.getBook().add(book3);


        // =========================
        // Save Categories
        // CascadeType.PERSIST
        // =========================

        Long programmingId = categoryService.save(programming);

        Long softwareId = categoryService.save(software);


        System.out.println("Categories saved successfully.");


        // =========================
        // Find Book
        // =========================

        System.out.println("========== FIND BOOK ==========");

        Book foundBook = bookService.findById(book1.getId()).orElse(null);

        if (foundBook != null) {

            System.out.println("Book found: " + foundBook);

        } else {

            System.out.println("Book not found.");
        }


        // =========================
        // Update Book
        // =========================

        System.out.println("========== UPDATE BOOK ==========");

        if (foundBook != null) {

            foundBook.setPrice(new BigDecimal("800000"));

            foundBook.setStockStatus(StockStatus.OUT_OF_STOCK);

            boolean updated = bookService.update(foundBook.getId(), foundBook);

            System.out.println("Book updated: " + updated);
        }


        // =========================
        // Delete Book
        // =========================

        System.out.println("========== DELETE BOOK ==========");

        boolean deleted = bookService.delete(book2.getId());

        System.out.println("Book deleted: " + deleted);


    }
}