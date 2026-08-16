package ir.maktabsharif.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDate birthDate;

    @OneToOne(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private Profile profile;

    @ManyToMany(mappedBy = "author")
    private List<Book> books;


    // Constructor برای JPA / Hibernate
    public Author() {
    }


    // Constructor برای Builder
    private Author(Builder builder) {
        this.name = builder.name;
        this.birthDate = builder.birthDate;
        this.profile = builder.profile;
        this.books = builder.books;
    }


    // Builder
    public static class Builder {

        private String name;
        private LocalDate birthDate;
        private Profile profile;
        private List<Book> books;


        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder birthDate(LocalDate birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public Builder profile(Profile profile) {
            this.profile = profile;
            return this;
        }

        public Builder books(List<Book> books) {
            this.books = books;
            return this;
        }


        public Author build() {
            return new Author(this);
        }
    }


    // Getters & Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }


    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                ", profile=" + profile +
                ", books=" + books +
                '}';
    }
}