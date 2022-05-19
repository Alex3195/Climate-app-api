package uz.alex.climateappapi.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import uz.alex.climateappapi.entity.ReferenceEntity;

@Getter
@Setter
public class ReferenceDto {
    private Long id;
    private String title;
    private String subtitle;
    private String author;
    private String publishedAt;
    private String publishedIn;
    private Long imgId;
    private Long bookFileId;

    public ReferenceEntity getEntity() {
        ReferenceEntity referenceEntity = new ReferenceEntity();
        referenceEntity.setId(getId());
        referenceEntity.setTitle(getTitle());
        referenceEntity.setSubtitle(getSubtitle());
        referenceEntity.setAuthor(getAuthor());
        referenceEntity.setPublishedIn(getPublishedIn());
        referenceEntity.setPublishedAt(getPublishedAt());
        referenceEntity.setImgId(getImgId());
        referenceEntity.setBookFileId(getBookFileId());
        return referenceEntity;
    }
}
