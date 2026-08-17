package ir.maktabsharif.repository.book;

import ir.maktabsharif.model.Book;
import ir.maktabsharif.repository.Repository;
import ir.maktabsharif.util.HibernateUtil;

import java.util.Optional;

public class BookRepository implements Repository<Book> {
    @Override
    public Long save(Book book) {
        return HibernateUtil.inTxResult(entityManager -> {
            entityManager.persist(book);
            return book.getId();
        });
    }

    @Override
    public Optional<Book> findById(Long id) {
        return HibernateUtil.inTxResult(em ->
                Optional.ofNullable(em.find(Book.class, id))
        );    }

    @Override
    public boolean update(Long id ,Book book) {

        Book book1 = HibernateUtil.inTxResult(em -> {

            Book book2 = em.find(Book.class, id);

            if (book2 == null) {
                return null;
            }

            book2.setTitle(book.getTitle());
            book2.setIsbn(book.getIsbn());
            book2.setPublicationYear(book.getPublicationYear());
            book2.setPrice(book.getPrice());
            book2.setStockStatus(book.getStockStatus());
            book2.setPublisherAddress(book.getPublisherAddress());
            book2.setAuthor(book.getAuthor());
            book2.setCategory(book.getCategory());

            return book2;
        });

        return book1 != null;
    }

    @Override
    public boolean delete(Long id) {
        HibernateUtil.inTxResult(eM -> {
            Book book = eM.find(Book.class, id);
            if (book == null) {
                return false;
            }
            eM.remove(book);
            return null;
        });
        return true;
    }
}
