package kh.edu.cstad.mbapi.controller;

import kh.edu.cstad.mbapi.dto.response.MediaResponse;
import kh.edu.cstad.mbapi.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/medias")
@RequiredArgsConstructor
public class MediaController {

    private final MediaService mediaService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/upload-single")
    public MediaResponse upload(@RequestPart MultipartFile file) {
        return mediaService.upload(file);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/upload-multiple")
    List<MediaResponse> uploadMultipleFiles(@RequestPart List<MultipartFile> files) {
        return mediaService.uploadMultipleFiles(files);
    }

    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity<Resource> download(@PathVariable String fileName) {
        return mediaService.download(fileName);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{fileName:.+}")
    public void deleteByName(@PathVariable String fileName) {
        mediaService.deleteByName(fileName);
    }

}
