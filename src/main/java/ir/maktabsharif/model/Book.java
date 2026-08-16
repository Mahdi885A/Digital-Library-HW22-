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


    public Book(String title, String isbn, int publicationYear, BigDecimal price, StockStatus stockStatus, PublisherAddress publisherAddress, List<Author> author, Category category) {
        this.title = title;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.price = price;
        this.stockStatus = stockStatus;
        this.publisherAddress = publisherAddress;
        this.author = author;
        this.category = category;
    }

    public Book() {
    }

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
