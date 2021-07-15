package cn.edu.nju.FindIt.service;

import java.io.File;
import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import cn.edu.nju.FindIt.utils.FileNameUtils;


import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
@Service
public class FileServiceImpl implements FileService{

    @Value("${web.upload-path}")
    private String path;

    private final ResourceLoader resourceLoader;

    @Autowired
    public FileServiceImpl(ResourceLoader resourceLoader){
        this.resourceLoader = resourceLoader;
    }

    @Override
    public ResponseEntity<String> uploadPhoto(MultipartFile file) {

        String realFileName = FileNameUtils.getFileName(file.getOriginalFilename());
        // 生成新的文件名
        String realPath = path + "/" + realFileName;
        File dest = new File(realPath);

        // 判断文件父目录是否存在
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdir();
        }

        try {
            // 保存文件
            file.transferTo(dest);
            return ResponseEntity.ok("http://localhost:8089/api/images/" + realFileName);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(realFileName);

    }

	@Override
	public ResponseEntity<Resource> downloadPhoto(String fileName) {
		try {
            Resource resource = resourceLoader.getResource("file:" + path + "/" + fileName);
            // 解析文件的 mime 类型
            String mediaTypeStr = URLConnection.getFileNameMap().getContentTypeFor(fileName);
            System.out.println(mediaTypeStr);
            // 无法判断MIME类型时，作为流类型
            mediaTypeStr = (mediaTypeStr == null) ? MediaType.APPLICATION_OCTET_STREAM_VALUE : mediaTypeStr;
            // 实例化MIME
            MediaType mediaType = MediaType.parseMediaType(mediaTypeStr);

            // 构造响应的头
            HttpHeaders headers = new HttpHeaders();
            // 下载之后需要在请求头中放置文件名，该文件名按照ISO_8859_1编码。
            String filenames = new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
            headers.setContentDispositionFormData("attachment", filenames);
            headers.setContentType(mediaType);

            // 返还资源
            return ResponseEntity.ok().headers(headers).contentLength(resource.getInputStream().available())
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
	}

	@Override
	public ResponseEntity<String> deletePhoto(String fileName) {
        String realPath = path + "/" + fileName;
        File dest = new File(realPath);

        if(dest.exists()){
            dest.delete();
            return ResponseEntity.ok().body("删除成功");
        }
        else{
            return ResponseEntity.notFound().build();
        }
	}
}
