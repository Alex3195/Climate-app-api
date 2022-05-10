package uz.alex.climateappapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.alex.climateappapi.dto.interfaces.TopicListInterface;
import uz.alex.climateappapi.entity.TopicEntity;

import java.util.List;
import java.util.Optional;

public interface TopicRepository extends JpaRepository<TopicEntity, Long>, JpaSpecificationExecutor<TopicEntity> {
    @Query(value = "select t.id                                                                                       as id,\n" +
            "       t.title                                                                                    as defaultTitle,\n" +
            "       CASE WHEN (tt.locale is null or tt.locale = :locale) THEN tt.title ELSE NULL END           as title,\n" +
            "       CASE WHEN (tt.locale is null or tt.locale = :locale) THEN tt.sub_title ELSE NULL END       as subTitle,\n" +
            "       CASE WHEN (tt.locale is null or tt.locale = :locale) THEN tt.content ELSE NULL END         as content\n" +
            "from topic_entity t\n" +
            "         left join topic_translation tt on tt.topic_id = t.id and (tt.locale is null or tt.locale = :locale)\n" +
            "where (t.status is null or t.status <> 'ARCHIVING');\n", nativeQuery = true)
    List<TopicListInterface> list_topic(@Param("locale") String locale);

    @Query(value = "select t.id as id,\n" +
            "       t.title as defaultTitle,\n" +
            "       CASE WHEN (tt.locale is null or tt.locale =:locale) THEN tt.title ELSE  NULL  END as title,\n" +
            "       CASE WHEN (tt.locale is null or tt.locale =:locale) THEN tt.sub_title ELSE  NULL  END as subTitle,\n" +
            "       CASE WHEN (tt.locale is null or tt.locale =:locale) THEN tt.content ELSE  NULL  END as content" +
            " from topic_entity t\n" +
            "       left join topic_translation tt on tt.topic_id = t.id and (tt.locale is null or tt.locale =:locale)\n" +
            "where t.id=:id and (t.status is null or t.status <> 'ARCHIVING')", nativeQuery = true)
    Optional<TopicListInterface> get_by_topic_id(@Param("locale") String locale,
                                                 @Param("id") Long id);
}


