package com.A4.UNCGTubeAPI.Analytics;

import com.A4.UNCGTubeAPI.Video.Video;
import jakarta.persistence.*;

@Entity
public class Analytics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "video_id", referencedColumnName = "id")
    private Video video;  // The associated Video

    private Long viewsCount = 0L;
    private Long commentCount = 0L;
    private Long likeCount = 0L;
    private Long dislikeCount = 0L;
    private Long revenue = 0L;  // Renamed for clarity
    private Long subscriberCount = 0L;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public Long getViewsCount() {
        return viewsCount;
    }

    public void setViewsCount(Long viewsCount) {
        this.viewsCount = viewsCount;
    }

    public Long getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Long commentCount) {
        this.commentCount = commentCount;
    }

    public Long getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Long likeCount) {
        this.likeCount = likeCount;
    }

    public Long getDislikeCount() {
        return dislikeCount;
    }

    public void setDislikeCount(Long dislikeCount) {
        this.dislikeCount = dislikeCount;
    }

    public Long getRevenue() {
        return revenue;
    }

    public void setAdRevenue(Long revenue) {
        this.revenue = revenue;
    }

    public Long getSubscriberCount() {
        return subscriberCount;
    }

    public void setSubscriberCount(Long subscriberCount) {
        this.subscriberCount = subscriberCount;
    }


    public Analytics orElse(Analytics analytics) {
        return analytics;
    }

    public String orElseThrow() {
        return "Couldn't find analytics for this video";
    }
}
