package ir.maktabsharif.repository.author;


import ir.maktabsharif.model.Author;
import ir.maktabsharif.repository.RepositoryFactory;
import ir.maktabsharif.repository.Repository;

public class AuthorRepositoryFactory extends RepositoryFactory<Author> {

    @Override
    public Repository<Author> createRepository() {
        return new AuthorRepository();
    }
}