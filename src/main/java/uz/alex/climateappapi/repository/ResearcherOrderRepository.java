package uz.alex.climateappapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.alex.climateappapi.entity.ResearchOrderEntity;

import java.util.Date;
import java.util.List;

@Repository
public interface ResearcherOrderRepository extends JpaRepository<ResearchOrderEntity, Long> {
    @Query("from ResearchOrderEntity  e where e.createdBy=:userId and e.status <> 'ARCHIVING'")
    List<ResearchOrderEntity> getAllOrdersUser(@Param("userId") Long userId);

    @Query("from ResearchOrderEntity e where e.status <>'ARCHIVING' and e.createdBy=:userId and e.id=:id")
    ResearchOrderEntity getByUserAndId(@Param("userId") Long userId, @Param("id") Long id);

    @Query("from ResearchOrderEntity e where e.status <>'ARCHIVING' and e.createdBy=:userId and e.createdDate between :fromDate and :toDate")
    List<ResearchOrderEntity> getByUserIdAndBetweenDate(@Param("userId") Long userId, @Param("fromDate") Date fromDate, @Param("toDate") Date toDate);


}
