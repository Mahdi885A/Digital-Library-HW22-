package ir.maktabsharif.service;

import ir.maktabsharif.repository.author.AuthorRepositoryFactory;
import ir.maktabsharif.model.Author;
import ir.maktabsharif.repository.Repository;

import java.util.Optional;

public class AuthorService {

    private final Repository<Author> authorRepository;

    public AuthorService() {
        AuthorRepositoryFactory factory = new AuthorRepositoryFactory();

        this.authorRepository = factory.createRepository();
    }

    public Long save(Author author) {
        return authorRepository.save(author);
    }


    public Optional<Author> findById(Long id) {
        return authorRepository.findById(id);
    }

    public boolean update(Long id ,Author author) {
        return authorRepository.update(id , author);
    }

    public boolean delete(Long id) {
        return authorRepository.delete(id);
    }
}