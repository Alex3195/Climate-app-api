package uz.alex.climateappapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.alex.climateappapi.entity.ResearchOrderEntity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface ResearcherOrderRepository extends JpaRepository<ResearchOrderEntity, Long> {
    @Query("from ResearchOrderEntity e where e.status <> 'ARCHIVING' and e.createdBy=:userId")
    List<ResearchOrderEntity> getAllOrdersByUser(@Param("userId") Long userId);

    @Query("from ResearchOrderEntity  e where e.createdBy=:userId and e.status <> 'ARCHIVING' and e.createdDate>= :fromDate and e.createdDate<=:toDate")
    List<ResearchOrderEntity> getAllOrdersBetweenDateByUser(@Param("userId") Long userId, @Param("fromDate") LocalDateTime from, @Param("toDate") LocalDateTime to);

    @Query("from ResearchOrderEntity e where e.status <>'ARCHIVING' and e.createdBy=:userId and e.id=:id")
    ResearchOrderEntity getByUserAndId(@Param("userId") Long userId, @Param("id") Long id);
}
