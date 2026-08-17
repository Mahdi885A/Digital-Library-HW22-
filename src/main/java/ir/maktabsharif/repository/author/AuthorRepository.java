package ir.maktabsharif.repository.author;

import ir.maktabsharif.model.Author;
import ir.maktabsharif.repository.Repository;
import ir.maktabsharif.util.HibernateUtil;

import java.util.Optional;

public class AuthorRepository implements Repository<Author> {

    @Override
    public Long save(Author author) {

        return HibernateUtil.inTxResult(em -> {
            em.persist(author);
            return author.getId();
        });
    }

    @Override
    public Optional<Author> findById(Long id) {

        return HibernateUtil.inTxResult(em ->
                Optional.ofNullable(
                        em.find(Author.class, id)
                )
        );
    }

    @Override
    public boolean update(Long id, Author author) {

        Author updatedAuthor = HibernateUtil.inTxResult(em -> {

            Author existingAuthor =
                    em.find(Author.class, id);

            if (existingAuthor == null) {
                return null;
            }

            existingAuthor.setName(author.getName());
            existingAuthor.setBirthDate(
                    author.getBirthDate()
            );
            existingAuthor.setProfile(
                    author.getProfile()
            );
            existingAuthor.setBooks(
                    author.getBooks()
            );

            return existingAuthor;
        });

        return updatedAuthor != null;
    }

    @Override
    public boolean delete(Long id) {

        return HibernateUtil.inTxResult(em -> {

            Author existingAuthor =
                    em.find(Author.class,id);

            if (existingAuthor == null) {
                return false;
            }

            em.remove(existingAuthor);
            return true;
        });
    }
}