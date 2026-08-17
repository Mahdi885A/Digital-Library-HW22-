package ir.maktabsharif.repository;

import java.util.Optional;

public interface Repository<T> {
        Long save(T t);

        Optional<T> findById(Long id);

        boolean update(Long id , T t);

        boolean delete (Long id);


}
