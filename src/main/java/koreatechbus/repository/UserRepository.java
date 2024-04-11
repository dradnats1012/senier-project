package koreatechbus.repository;

import koreatechbus.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findBySchoolId(String schoolId);
    User findByUserId(Long userId);

    Boolean existsBySchoolId(String schoolId);

    Boolean existsByEmail(String email);
}
