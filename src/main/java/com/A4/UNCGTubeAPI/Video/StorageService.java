package com.A4.UNCGTubeAPI.Video;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class StorageService {

        private final Path videoStorageLocation = Paths.get("uploads/videos").toAbsolutePath().normalize();

        // Store video file and return the video URL
        public String storeVideo(MultipartFile videoFile) {
            try {
                Files.createDirectories(videoStorageLocation);
                String filename = StringUtils.cleanPath(videoFile.getOriginalFilename());
                Path targetLocation = videoStorageLocation.resolve(filename);
                Files.copy(videoFile.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
                return "/uploads/videos/" + filename;  // Return the relative URL for the video
            } catch (IOException ex) {
                throw new RuntimeException("Could not store video file: " + videoFile.getOriginalFilename(), ex);
            }
        }
    }

