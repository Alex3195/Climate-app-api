package uz.alex.climateappapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.alex.climateappapi.entity.TopicFileEntity;

public interface TopicFileRepository extends JpaRepository<TopicFileEntity, Long>, JpaSpecificationExecutor<TopicFileEntity> {

    @Modifying
    @Query(value = "DELETE FROM topic_file_entity where id =:fileId", nativeQuery = true)
    void deleteTopicFile(@Param("fileId") Long fileId);
}
