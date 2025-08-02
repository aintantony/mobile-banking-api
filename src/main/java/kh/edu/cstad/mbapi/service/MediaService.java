package kh.edu.cstad.mbapi.service;

import kh.edu.cstad.mbapi.dto.response.MediaResponse;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Service interface for handling media operations such as uploading files.
 * @author aintantony
 */
public interface MediaService {

    /**
     * Uploads a single media file.
     *
     * @param file the file to upload
     * @return a response containing the uploaded media's information
     */
    MediaResponse upload(MultipartFile file);

    /**
     * Uploads multiple media files.
     *
     * @param files a list of files to upload
     * @return a list of responses, each containing information about an uploaded media file
     */
    List<MediaResponse> uploadMultipleFiles(List<MultipartFile> files);

    ResponseEntity<Resource> download(String fileName);

    void deleteByName(String fileName);

}
