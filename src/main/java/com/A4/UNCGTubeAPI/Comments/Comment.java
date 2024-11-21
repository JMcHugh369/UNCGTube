package com.A4.UNCGTubeAPI.Comments;

import com.A4.UNCGTubeAPI.Provider.Provider;
import com.A4.UNCGTubeAPI.Video.Video;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    @ManyToOne
    @JoinColumn(name = "video_id", nullable = false)
    private Video video; // Link comment to video

    @ManyToOne
    @JoinColumn(name = "provider_id")
    private Provider provider;

    @ManyToOne
    @JoinColumn(name = "parent_comment_id")
    private Comment parentComment;


    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return text;
    }

    public void setContent(String content) {
        this.text = content;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public Comment getParentComment() {
        return parentComment;
    }

    public void setParentComment(Comment parentComment) {
        this.parentComment = parentComment;
    }

    public void setProvider(Provider provider) {
    }

    public Provider getProvider() {
        return null;
    }
}
