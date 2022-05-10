package uz.alex.climateappapi.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface TopicFileService {

    Long saveTopicFile(MultipartFile file, Long topicId);

    Long editTopicFile(MultipartFile file, Long topicId, Long id);

    void deleteTopicFile(Long id);

    Resource loadAsResourceTopicFile(Long id);
}
