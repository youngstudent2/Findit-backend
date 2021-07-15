package cn.edu.nju.FindIt.controller;

import cn.edu.nju.FindIt.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Api(tags = { "图片上传下载接口" })
@RequestMapping("/api/images")
@RestController
public class ImageController {

    private final FileService fileService;

    @Autowired
    public ImageController(FileService fileService) {
        this.fileService = fileService;
    }

    @ApiOperation(value = "上传照片", notes = "")
    @PostMapping("")
    public ResponseEntity<String> uploadPhoto(
            @ApiParam(value = "图片", required = true) @RequestParam("file") MultipartFile file) {
        return fileService.uploadPhoto(file);
    }

    @ApiOperation(value = "下载照片", notes = "")
    @GetMapping("/{fileName}")
    public ResponseEntity<Resource> downloadPhoto(
            @ApiParam(value = "图片名称", required = true) @PathVariable("fileName") String fileName) {
        return fileService.downloadPhoto(fileName);
    }

    @ApiOperation(value = "删除照片", notes = "")
    @DeleteMapping("/{fileName}")
    public ResponseEntity<String> delete(
            @ApiParam(value = "图片名称", required = true) @PathVariable("fileName") String fileName) {
        return fileService.deletePhoto(fileName);
    }
}
