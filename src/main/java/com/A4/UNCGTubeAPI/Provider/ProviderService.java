package com.A4.UNCGTubeAPI.Provider;

import com.A4.UNCGTubeAPI.Video.Video;
import com.A4.UNCGTubeAPI.Comments.Comment;
import com.A4.UNCGTubeAPI.Analytics.Analytics;
import com.A4.UNCGTubeAPI.Video.videoRepo;
import com.A4.UNCGTubeAPI.Comments.commentRepo;
import com.A4.UNCGTubeAPI.Analytics.analyticsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProviderService {

    @Autowired
    private ProviderRepo providerRepo;

    @Autowired
    private videoRepo videoRepo;

    @Autowired
    private commentRepo commentRepo;

    @Autowired
    private analyticsRepo analyticsRepo;

    public List<Provider> getAllProviders() {
        return providerRepo.findAll();
    }

    public Provider saveProvider(Provider provider) {
        return providerRepo.save(provider);
    }

    public Provider updateProvider(Long id, Provider updatedProvider) {
        Optional<Provider> optionalProvider = providerRepo.findById(id);

        if (optionalProvider.isPresent()) {
            Provider existingProvider = optionalProvider.get();
            existingProvider.setName(updatedProvider.getName());
            existingProvider.setBio(updatedProvider.getBio());
            return providerRepo.save(existingProvider);
        } else {
            throw new RuntimeException("Provider not found with ID: " + id);
        }
    }

    public void deleteProvider(Long id) {
        providerRepo.deleteById(id);
    }

    // Get all videos by provider
    public List<Video> getVideosByProvider(Long providerId) {
        Optional<Provider> providerOpt = providerRepo.findById(providerId);
        if (providerOpt.isPresent()) {
            return providerOpt.get().getVideos();
        } else {
            throw new RuntimeException("Provider not found with ID: " + providerId);
        }
    }

    // Get all comments for a specific video
    public List<Comment> getCommentsForVideo(Long videoId) {
        Optional<Video> videoOpt = videoRepo.findById(videoId);
        if (videoOpt.isPresent()) {
            return videoOpt.get().getComments();
        } else {
            throw new RuntimeException("Video not found with ID: " + videoId);
        }
    }

    // Get analytics for a specific video
    public Analytics getAnalyticsForVideo(Long videoId) {
        Optional<Video> videoOpt = videoRepo.findById(videoId);
        if (videoOpt.isPresent()) {
            Analytics analytics = videoOpt.get().getAnalytics();
            if (analytics != null) {
                return analytics;
            } else {
                throw new RuntimeException("Analytics not found for video ID: " + videoId);
            }
        } else {
            throw new RuntimeException("Video not found with ID: " + videoId);
        }
    }

    public Provider getProviderById(Long id) {
        return providerRepo.findById(id).orElse(null);
    }

    public void uploadVideo(Video video) {
    }
}
