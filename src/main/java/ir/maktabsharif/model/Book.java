package ir.maktabsharif.model;

import ir.maktabsharif.enums.StockStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String isbn;

    @Column(name = "publication_year")
    private int publicationYear;

    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private StockStatus stockStatus;

    @Embedded
    private PublisherAddress publisherAddress;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Author> author;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


    // Constructor برای Hibernate
    public Book() {
    }


    // Constructor برای Builder
    private Book(Builder builder) {
        this.title = builder.title;
        this.isbn = builder.isbn;
        this.publicationYear = builder.publicationYear;
        this.price = builder.price;
        this.stockStatus = builder.stockStatus;
        this.publisherAddress = builder.publisherAddress;
        this.author = builder.author;
        this.category = builder.category;
    }


    // Builder
    public static class Builder {

        private String title;
        private String isbn;
        private int publicationYear;
        private BigDecimal price;
        private StockStatus stockStatus;
        private PublisherAddress publisherAddress;
        private List<Author> author;
        private Category category;


        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder isbn(String isbn) {
            this.isbn = isbn;
            return this;
        }

        public Builder publicationYear(int publicationYear) {
            this.publicationYear = publicationYear;
            return this;
        }

        public Builder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Builder stockStatus(StockStatus stockStatus) {
            this.stockStatus = stockStatus;
            return this;
        }

        public Builder publisherAddress(PublisherAddress publisherAddress) {
            this.publisherAddress = publisherAddress;
            return this;
        }

        public Builder author(List<Author> author) {
            this.author = author;
            return this;
        }

        public Builder category(Category category) {
            this.category = category;
            return this;
        }


        public Book build() {
            return new Book(this);
        }
    }


    // Getters & Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public StockStatus getStockStatus() {
        return stockStatus;
    }

    public void setStockStatus(StockStatus stockStatus) {
        this.stockStatus = stockStatus;
    }

    public PublisherAddress getPublisherAddress() {
        return publisherAddress;
    }

    public void setPublisherAddress(PublisherAddress publisherAddress) {
        this.publisherAddress = publisherAddress;
    }

    public List<Author> getAuthor() {
        return author;
    }

    public void setAuthor(List<Author> author) {
        this.author = author;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", publicationYear=" + publicationYear +
                ", price=" + price +
                ", stockStatus=" + stockStatus +
                ", publisherAddress=" + publisherAddress +
                ", author=" + author +
                ", category=" + category +
                '}';
    }
}