package uz.alex.climateappapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.alex.climateappapi.entity.ReviewImageEntity;


public interface ReviewImageRepository extends JpaRepository<ReviewImageEntity, Long>, JpaSpecificationExecutor<ReviewImageEntity> {

    @Modifying
    @Query(value = "INSERT INTO review_image (image_file_id, topic_review_id)\n" +
                   "VALUES (:imageFileId,:topicReviewId)", nativeQuery = true)
    void insert_review_image(@Param("imageFileId") Long imageFileId,
                             @Param("topicReviewId") Long topicReviewId);
}
