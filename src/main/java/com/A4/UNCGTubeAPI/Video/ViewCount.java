package com.A4.UNCGTubeAPI.Video;

import jakarta.persistence.*;

@Entity
public class ViewCount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "video_id")
    private Video video;

    private int count;

    public int getCount() {
        return count;
    }

    public Long getId() {
        return id;
    }

    public Video getVideo() {
        return video;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setVideo(Video video) {
        this.video = video;
    }
}
