package ir.maktabsharif.repository.profile;


import ir.maktabsharif.model.Profile;
import ir.maktabsharif.repository.Repository;
import ir.maktabsharif.repository.RepositoryFactory;

public class ProfileRepositoryFactory extends RepositoryFactory<Profile> {

    @Override
    public Repository<Profile> createRepository() {
        return new ProfileRepository();
    }
}