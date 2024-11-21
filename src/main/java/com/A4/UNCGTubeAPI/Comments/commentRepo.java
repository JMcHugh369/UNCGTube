package com.A4.UNCGTubeAPI.Comments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface commentRepo extends JpaRepository<Comment, Long> {

    List<Comment> findByVideoId(Long videoId);
}
