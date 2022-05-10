package uz.alex.climateappapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.alex.climateappapi.entity.referenceData.ImageFileEntity;


public interface ImageFileRepository extends JpaRepository<ImageFileEntity, Long>, JpaSpecificationExecutor<ImageFileEntity> {

    @Modifying
    @Query(value = "DELETE FROM image_file_entity where id =:fileId", nativeQuery = true)
    void deleteImageFile(@Param("fileId") Long fileId);
}
