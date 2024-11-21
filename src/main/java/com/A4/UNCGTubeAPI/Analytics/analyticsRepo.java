package com.A4.UNCGTubeAPI.Analytics;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface analyticsRepo extends JpaRepository<Analytics, Long> {

    Analytics findByVideoId(Long videoId);

    void deleteByVideoId(Long videoId);

    boolean existsByVideoId(Long videoId);
}
