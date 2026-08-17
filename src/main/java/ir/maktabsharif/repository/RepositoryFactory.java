package ir.maktabsharif.repository;

public abstract class RepositoryFactory<T> {

    public abstract Repository<T> createRepository();
}