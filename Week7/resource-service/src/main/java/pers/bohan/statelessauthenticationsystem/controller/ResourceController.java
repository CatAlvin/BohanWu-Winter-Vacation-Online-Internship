package pers.bohan.statelessauthenticationsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * OAuth2 Log in controller.
 *
 * @author bohan
 */
@RestController
@RequestMapping("/resources")
public class ResourceController {

    @Autowired
    private ResourceLoader resourceLoader;

    // 本地资源路径, 通过配置文件注入, application.yml 中配置
    @Value("${resource.local-path}")
    private String localPath;

    @GetMapping("/images/{imageId}")
    public ResponseEntity<Resource> getImage(@PathVariable String imageId) {
        String imagePath = localPath + "/images/" + imageId;
        Resource resource = resourceLoader.getResource("file:" + imagePath);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(resource);
    }

    @GetMapping("/videos/{videoId}")
    public ResponseEntity<Resource> getVideo(@PathVariable String videoId) {
        String videoPath = localPath + "/videos/" + videoId;
        Resource resource = resourceLoader.getResource("file:" + videoPath);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + videoId + "\"")
                .body(resource);
    }

    @GetMapping("/files/{fileId}")
    public ResponseEntity<Resource> getFile(@PathVariable String fileId) {
        String filePath = localPath + "/files/" + fileId;
        Resource resource = resourceLoader.getResource("file:" + filePath);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileId + "\"")
                .body(resource);
    }
}

