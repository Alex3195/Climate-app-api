package uz.alex.climateappapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.alex.climateappapi.entity.TopicCategoryTranslationEntity;

public interface TopicCategoryTranslationRepository extends JpaRepository<TopicCategoryTranslationEntity, Long>, JpaSpecificationExecutor<TopicCategoryTranslationEntity> {

    @Modifying
    @Query(value = "INSERT INTO topic_category_translation (topic_category_id, title, sub_title, locale)\n" +
    "VALUES (:topicCategoryId, :title, :subTitle, :localeLang)\n " +
    "ON CONFLICT (topic_category_id, locale)\n" +
    "   DO UPDATE SET title = excluded.title,\n" +
    "                 sub_title = excluded.sub_title", nativeQuery = true)
    void insert_update_topic_category_translation(@Param("topicCategoryId") Long topicCategoryId,
                                                  @Param("title") String title,
                                                  @Param("subTitle") String subTitle,
                                                  @Param("localeLang") String locale);
}
