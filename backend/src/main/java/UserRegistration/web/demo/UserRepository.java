package UserRegistration.web.demo;

import UserRegistration.web.demo.UserEntity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    UserEntity findByUserName(String name);
    boolean existsByEmailId(String emailId);
    Optional<UserEntity> findByEmailId(String emailId);
}
