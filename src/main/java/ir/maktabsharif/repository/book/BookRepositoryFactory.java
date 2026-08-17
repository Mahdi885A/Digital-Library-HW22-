package ir.maktabsharif.repository.book;

import ir.maktabsharif.model.Book;
import ir.maktabsharif.repository.RepositoryFactory;
import ir.maktabsharif.repository.Repository;

public class BookRepositoryFactory extends RepositoryFactory<Book> {

    @Override
    public Repository<Book> createRepository() {
        return new BookRepository();
    }
}