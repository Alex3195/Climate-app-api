package uz.alex.climateappapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import uz.alex.climateappapi.dto.interfaces.UserListInterFace;
import uz.alex.climateappapi.entity.UserEntity;

import java.util.Optional;
@EnableJpaRepositories
public interface UserRepository extends JpaRepository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity> {
    Optional<UserEntity> findByUsernameIgnoreCase(String username);

    @Query(value = "select" +
            "       u.id         as id,\n" +
            "       u.is_active  as isActive,\n" +
            "       u.username   as userName,\n" +
            "       u.full_name  as fullName," +
            "       u.role       as role" +
            " from users u\n" +
            "where u.status <> 'DELETED'\n",
            countQuery = "select COUNT(u.id) from users u where u.status <> 'DELETED'", nativeQuery = true)
    Page<UserListInterFace> findAllListPage(Pageable pageable);
}
