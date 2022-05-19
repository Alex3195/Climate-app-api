package uz.alex.climateappapi.entity;

import lombok.Getter;
import lombok.Setter;
import uz.alex.climateappapi.entity.base.BaseServerModifierEntity;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class ReferenceEntity extends BaseServerModifierEntity {
    @Column(name = "title")
    private String title;
    @Column(name = "subtitle")
    private String subtitle;
    @Column(name = "author")
    private String author;
    @Column(name = "published_at")
    private String publishedAt;
    @Column(name = "published_in")
    private String publishedIn;
    @Column(name = "img_id")
    private Long imgId;

    @Column(name = "book_file_id")
    private Long bookFileId;

}
