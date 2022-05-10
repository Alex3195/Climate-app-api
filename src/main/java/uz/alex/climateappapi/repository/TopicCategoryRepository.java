package uz.alex.climateappapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.alex.climateappapi.dto.interfaces.TopicCategoryListInterface;
import uz.alex.climateappapi.entity.TopicCategoryEntity;

import java.util.List;
import java.util.Optional;

public interface TopicCategoryRepository extends JpaRepository<TopicCategoryEntity, Long>, JpaSpecificationExecutor<TopicCategoryEntity> {

    @Query(value = "select tc.id                                                                                   as id,\n" +
            "       tc.display_icon_id                                                                      as displayIconId,\n" +
            "       tc.parent_topic_category_id                                                             as parentId,\n" +
            "       tc.title                                                                                as defaultTitle,\n" +
            "       tctp.title                                                                                as parentTitle,\n" +
            "       CASE WHEN (tct.locale is null or tct.locale = :locale) THEN tct.title ELSE NULL END     as title,\n" +
            "       CASE WHEN (tct.locale is null or tct.locale = :locale) THEN tct.sub_title ELSE NULL END as subTitle\n" +
            "from topic_category_entity tc\n" +
            "         left join topic_category_translation tct\n" +
            "                   on tc.id = tct.topic_category_id and (tct.locale is null or tct.locale = :locale)\n" +
            "         left join topic_category_translation tctp on tc.parent_topic_category_id = tctp.topic_category_id and\n" +
            "                                                       (tct.locale is null or tct.locale = :locale)\n" +
            "where (tc.status is null or tc.status <> 'ARCHIVING')", nativeQuery = true)
    List<TopicCategoryListInterface> list_topicCategory(@Param("locale") String locale);

    @Query(value = "select tc.id as id,\n" +
            "       tc.display_icon_id as displayIconId,\n" +
            "       tc.parent_topic_category_id as parentId,\n" +
            "       tc.title as defaultTitle,\n" +
            "       CASE WHEN (tct.locale is null or tct.locale =:locale) THEN tct.title ELSE  NULL  END as title,\n" +
            "       CASE WHEN (tct.locale is null or tct.locale =:locale) THEN tct.sub_title ELSE  NULL  END as subTitle \n" +
            "from topic_category_entity tc\n" +
            "left join topic_category_translation tct on tc.id = tct.topic_category_id and (tct.locale is null or tct.locale =:locale)\n" +
            "where tc.id =:id and (tc.status is null or tc.status<> 'ARCHIVING')", nativeQuery = true)
    Optional<TopicCategoryListInterface> get_by_topicCategory_id(@Param("locale") String locale,
                                                                 @Param("id") Long id);

    @Query(value = "select tc.id as id,\n" +
            "       tc.display_icon_id as displayIconId,\n" +
            "       tc.parent_topic_category_id as parentId,\n" +
            "       tc.title as defaultTitle,\n" +
            "       CASE WHEN (tct.locale is null or tct.locale =:locale) THEN tct.title ELSE  NULL  END as title,\n" +
            "       CASE WHEN (tct.locale is null or tct.locale =:locale) THEN tct.sub_title ELSE  NULL  END as subTitle \n" +
            "from topic_category_entity tc\n" +
            "left join topic_category_translation tct on tc.id = tct.topic_category_id and (tct.locale is null or tct.locale =:locale)\n" +
            "where tc.id !=:id and (tc.status is null or tc.status<> 'ARCHIVING')", nativeQuery = true)
    List<TopicCategoryListInterface> get_by_topicCategory_list_id(@Param("locale") String locale,
                                                                  @Param("id") Long id);

    @Modifying
    @Query(value = "UPDATE topic_category_entity\n" +
            "       SET display_icon_id  = (CASE WHEN display_icon_id  = CAST(:imageFileId as bigint) THEN NULL ELSE display_icon_id END)", nativeQuery = true)
    void un_bind_image_file_id(@Param("imageFileId") Long imageFileId);


}
