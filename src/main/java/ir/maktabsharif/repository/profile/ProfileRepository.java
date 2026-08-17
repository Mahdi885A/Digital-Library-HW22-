package ir.maktabsharif.repository.profile;

import ir.maktabsharif.model.Profile;
import ir.maktabsharif.repository.Repository;
import ir.maktabsharif.util.HibernateUtil;

import java.util.Optional;

public class ProfileRepository implements Repository<Profile> {

    @Override
    public Long save(Profile profile) {

        return HibernateUtil.inTxResult(em -> {
            em.persist(profile);
            return profile.getId();
        });
    }

    @Override
    public Optional<Profile> findById(Long id) {

        return HibernateUtil.inTxResult(em ->
                Optional.ofNullable(
                        em.find(Profile.class, id)
                )
        );
    }

    @Override
    public boolean update(Long id , Profile profile) {

        Profile updatedProfile = HibernateUtil.inTxResult(em -> {

            Profile existingProfile =
                    em.find(Profile.class, profile.getId());

            if (existingProfile == null) {
                return null;
            }

            existingProfile.setBio(profile.getBio());
            existingProfile.setWebSite(profile.getWebSite());
            existingProfile.setAuthor(profile.getAuthor());

            return existingProfile;
        });

        return updatedProfile != null;
    }

    @Override
    public boolean delete(Long id) {

        return HibernateUtil.inTxResult(em -> {

            Profile existingProfile =
                    em.find(Profile.class, id);

            if (existingProfile == null) {
                return false;
            }

            em.remove(existingProfile);
            return true;
        });
    }
}