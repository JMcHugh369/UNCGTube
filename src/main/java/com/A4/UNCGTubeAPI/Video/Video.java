package com.A4.UNCGTubeAPI.Video;

import com.A4.UNCGTubeAPI.Analytics.Analytics;
import com.A4.UNCGTubeAPI.Comments.Comment;
import com.A4.UNCGTubeAPI.Provider.Provider;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String url;
    private int viewCount;
    private String thumbnail;

    @ManyToOne
    @JoinColumn(name = "provider_id", nullable = false)
    private Provider provider;

    @OneToOne(mappedBy = "video", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "video_id")
    private Analytics analytics;

    public Video(String title, String description, String videoUrl, int i) {
    }

    public Video() {

    }


    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Analytics getAnalytics() {

        return analytics;
    }

    public void setAnalytics(Analytics analytics) {
        this.analytics = analytics;
    }

    public List<Comment> getContent() {
        return List.of();
    }

    public List<Comment> getComments() {
        return List.of();
    }

    public void setProviderId(Long providerId) {
    }
}
