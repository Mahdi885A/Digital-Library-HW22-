package ir.maktabsharif.service;

import ir.maktabsharif.repository.book.BookRepositoryFactory;
import ir.maktabsharif.model.Book;
import ir.maktabsharif.repository.Repository;

import java.util.Optional;

public class BookService {

    private final Repository<Book> bookRepository;

    public BookService() {
        BookRepositoryFactory factory = new BookRepositoryFactory();

        this.bookRepository = factory.createRepository();
    }

    public Long save(Book book) {
        return bookRepository.save(book);
    }

    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    public boolean update(Long id ,Book book) {
        return bookRepository.update(id,book);
    }

    public boolean delete(Long id) {
        return bookRepository.delete(id);
    }
}