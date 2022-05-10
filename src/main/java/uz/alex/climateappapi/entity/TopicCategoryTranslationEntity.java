package uz.alex.climateappapi.entity;

import lombok.Getter;
import lombok.Setter;
import uz.alex.climateappapi.entity.base.BaseServerModifierEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Getter
@Setter
@Entity
@Table(name = "topic_category_translation",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"topicCategoryId", "locale"})})
public class TopicCategoryTranslationEntity extends BaseServerModifierEntity {

    @Column(name = "title")
    private String title;

    @Column(name = "subTitle")
    private String subTitle;

    @Column(name = "locale")
    private String locale;

    @Column(name = "topicCategoryId")
    private Long topicCategoryId;
}
