package ir.maktabsharif.repository.category;

import ir.maktabsharif.model.Category;
import ir.maktabsharif.repository.Repository;
import ir.maktabsharif.util.HibernateUtil;

import java.util.Optional;

public class CategoryRepository implements Repository<Category> {

    @Override
    public Long save(Category category) {

        return HibernateUtil.inTxResult(em -> {
            em.persist(category);
            return category.getId();
        });
    }

    @Override
    public Optional<Category> findById(Long id) {

        return HibernateUtil.inTxResult(em ->
                Optional.ofNullable(
                        em.find(Category.class, id)
                )
        );
    }

    @Override
    public boolean update(Long id , Category category) {

        Category updatedCategory =
                HibernateUtil.inTxResult(em -> {

                    Category existingCategory =
                            em.find(
                                    Category.class, id);

                    if (existingCategory == null) {
                        return null;
                    }

                    existingCategory.setName(category.getName());

                    existingCategory.setBook(category.getBook());

                    return existingCategory;
                });

        return updatedCategory != null;
    }

    @Override
    public boolean delete(Long  id) {

        return HibernateUtil.inTxResult(em -> {

            Category existingCategory = em.find(Category.class,id);

            if (existingCategory == null) {
                return false;
            }

            em.remove(existingCategory);
            return true;
        });
    }
}