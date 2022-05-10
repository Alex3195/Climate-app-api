package uz.alex.climateappapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.alex.climateappapi.dto.interfaces.TopicReviewListInterface;
import uz.alex.climateappapi.entity.TopicReviewEntity;

import java.util.List;

public interface TopicReviewRepository extends JpaRepository<TopicReviewEntity, Long>, JpaSpecificationExecutor<TopicReviewEntity> {

    @Query(value = "select tr.id        as id,\n" +
            "       tr.comment          as comment,\n" +
            "       tr.given_rate       as givenRate,\n" +
            "       tr.reply_to_comment as replyToCommentId,\n" +
            "       tr.topic_id         as topicId,\n" +
            "       trc.comment         as replayToComment,\n" +
            "       ri.image_file_id    as imageFileId\n" +
            "from topic_review_entity tr\n" +
            "         left join topic_review_entity trc on tr.id = trc.reply_to_comment\n" +
            "         left join review_image ri on tr.id = ri.topic_review_id\n" +
            "where (tr.status is null or tr.status <> 'ARCHIVING')", nativeQuery = true)
    List<TopicReviewListInterface> list_topic_review(@Param("id") Long id);


}
