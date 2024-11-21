package com.A4.UNCGTubeAPI.Analytics;

import com.A4.UNCGTubeAPI.Video.Video;
import com.A4.UNCGTubeAPI.Video.videoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class analyticsServ {

    @Autowired
    private analyticsRepo analyticsRepo;

    @Autowired
    private videoRepo videoRepo; // Injecting Video repository to fetch video info

    // Get analytics by videoId
    public Analytics getAnalyticsByVideoId(Long videoId) {
        // Fetch the video using the videoId
        Video video = videoRepo.findById(videoId)
                .orElseThrow(() -> new RuntimeException("Video not found for video ID: " + videoId));

        // Now, get the analytics for that video
        Analytics analytics = analyticsRepo.findByVideoId(videoId);
        // Make sure the video information is correctly set on the Analytics object
        analytics.setVideo(video);

        return analytics;
    }

    // Create or update analytics for a video
    public Analytics updateAnalytics(Long videoId, Long viewsCount, Long commentCount, Long subscriberCount) {
        // Fetch the video
        Video video = videoRepo.findById(videoId)
                .orElseThrow(() -> new RuntimeException("Video not found for video ID: " + videoId));

        // Fetch or create new Analytics object
        Analytics analytics = analyticsRepo.findByVideoId(videoId)
                .orElse(new Analytics());

        // Set video and update counts
        analytics.setVideo(video);
        analytics.setViewsCount(viewsCount);
        analytics.setCommentCount(commentCount);
        analytics.setSubscriberCount(subscriberCount);

        // Calculate adRevenue based on views (assuming $0.50 per view)
        Long adRevenue = viewsCount * 50; // $0.50 per view, so 50 cents = 50 in cents
        analytics.setAdRevenue(adRevenue);

        return analyticsRepo.save(analytics);
    }

    // Increment the view count for a video
    public Analytics incrementViews(Long videoId) {
        Video video = videoRepo.findById(videoId)
                .orElseThrow(() -> new RuntimeException("Video not found for video ID: " + videoId));

        Analytics analytics = analyticsRepo.findByVideoId(videoId)
                .orElse(new Analytics());

        // Increment view count
        analytics.setVideo(video);
        analytics.setViewsCount(analytics.getViewsCount() + 1);

        // Recalculate revenue after increment
        Long adRevenue = analytics.getViewsCount() * 50; // $0.50 per view
        analytics.setAdRevenue(adRevenue);

        return analyticsRepo.save(analytics);
    }

    // Increment the comment count for a video
    public Analytics incrementComments(Long videoId) {
        Video video = videoRepo.findById(videoId)
                .orElseThrow(() -> new RuntimeException("Video not found for video ID: " + videoId));

        Analytics analytics = analyticsRepo.findByVideoId(videoId)
                .orElse(new Analytics());

        // Increment comment count
        analytics.setVideo(video);
        analytics.setCommentCount(analytics.getCommentCount() + 1);

        return analyticsRepo.save(analytics);
    }

    // Increment subscriber count for a video (if applicable)
    public Analytics incrementSubscribers(Long videoId, Long newSubscribers) {
        Video video = videoRepo.findById(videoId)
                .orElseThrow(() -> new RuntimeException("Video not found for video ID: " + videoId));

        Analytics analytics = analyticsRepo.findByVideoId(videoId)
                .orElse(new Analytics());

        // Increment subscriber count
        analytics.setVideo(video);
        analytics.setSubscriberCount(analytics.getSubscriberCount() + newSubscribers);

        return analyticsRepo.save(analytics);
    }

    // Delete analytics by videoId
    public void deleteAnalytics(Long videoId) {
        Video video = videoRepo.findById(videoId)
                .orElseThrow(() -> new RuntimeException("Video not found for video ID: " + videoId));

        Analytics analytics = analyticsRepo.findByVideoId(videoId);

        analyticsRepo.delete(analytics);
    }
}
