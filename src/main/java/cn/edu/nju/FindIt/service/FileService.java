package cn.edu.nju.FindIt.service;

import org.springframework.http.ResponseEntity;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传工具包
 */
public interface FileService {
    ResponseEntity<String> uploadPhoto(MultipartFile file);

    ResponseEntity<Resource> downloadPhoto(String fileName);

    ResponseEntity<String> deletePhoto(String fileName);
}