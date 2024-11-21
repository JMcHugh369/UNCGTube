package com.A4.UNCGTubeAPI.Video;

import com.A4.UNCGTubeAPI.Provider.Provider;
import com.A4.UNCGTubeAPI.Provider.ProviderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public class videoServ {

    @Autowired
    private videoRepo videoRepository;

    @Autowired
    private ProviderRepo providerRepo;

    @Autowired
    private StorageService storageService;  // This will handle file storage (like in your previous message)

    // Increment the view count when a user watches the video
    public void incrementViewCount(Long videoId) {
        Video video = videoRepository.findById(videoId).orElseThrow(() -> new RuntimeException("Video not found"));
        video.setViewCount(video.getViewCount() + 1);
        videoRepository.save(video);
    }

    // Get video by ID
    public Video getVideoById(Long id) {
        return videoRepository.findById(id).orElseThrow(() -> new RuntimeException("Video not found"));
    }

    // Handle video upload and save to the database
    public void uploadVideo(Long providerId, String title, String description, MultipartFile videoFile) {

    }

    public void saveVideo(Video video) {
    }
}
