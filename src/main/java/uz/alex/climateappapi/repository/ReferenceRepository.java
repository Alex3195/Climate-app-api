package uz.alex.climateappapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.alex.climateappapi.entity.ReferenceEntity;

import java.util.List;

@Repository
public interface ReferenceRepository extends JpaRepository<ReferenceEntity, Long> {

    @Query(value = "from ReferenceEntity e")
    List<ReferenceEntity> getAllReferences();

}
