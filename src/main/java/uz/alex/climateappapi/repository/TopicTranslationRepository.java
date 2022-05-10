package uz.alex.climateappapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.alex.climateappapi.entity.TopicTranslationEntity;

public interface TopicTranslationRepository extends JpaRepository<TopicTranslationEntity, Long>, JpaSpecificationExecutor<TopicTranslationEntity> {

    @Modifying
    @Query(value = "INSERT INTO topic_translation (topic_id, title, sub_title, content, locale)\n" +
            "VALUES (:topicId, :title, :subTitle, :content, :localeLang)\n" +
            "ON CONFLICT (topic_id,locale)\n" +
            "    DO UPDATE SET title = excluded.title,\n" +
            "                  content = excluded.content,\n" +
            "                  sub_title = excluded.sub_title", nativeQuery = true)
    void insert_update_topic_translation(@Param("topicId") Long topicId,
                                         @Param("title") String title,
                                         @Param("subTitle") String subTitle,
                                         @Param("content") String content,
                                         @Param("localeLang") String locale);
}


