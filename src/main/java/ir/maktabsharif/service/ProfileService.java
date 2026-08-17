package ir.maktabsharif.service;

import ir.maktabsharif.repository.profile.ProfileRepositoryFactory;
import ir.maktabsharif.model.Profile;
import ir.maktabsharif.repository.Repository;

import java.util.Optional;

public class ProfileService {

    private final Repository<Profile> profileRepository;

    public ProfileService() {
        ProfileRepositoryFactory factory = new ProfileRepositoryFactory();

        this.profileRepository = factory.createRepository();
    }

    public Long save(Profile profile) {
        return profileRepository.save(profile);
    }

    public Optional<Profile> findById(Long id) {
        return profileRepository.findById(id);
    }

    public boolean update(Long id , Profile profile) {
        return profileRepository.update(id , profile);
    }

    public boolean delete(Long id) {
        return profileRepository.delete(id);
    }
}