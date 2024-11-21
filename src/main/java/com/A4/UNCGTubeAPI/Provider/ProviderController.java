package com.A4.UNCGTubeAPI.Provider;

import com.A4.UNCGTubeAPI.Video.Video;
import com.A4.UNCGTubeAPI.Video.videoServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/providers")
public class ProviderController {

    @Autowired
    private ProviderService providerService;

    @Autowired
    private videoServ videoService;

    // Display provider profile
    @GetMapping("/{id}")
    public String getProviderProfile(@PathVariable Long id, Model model) {
        Provider provider = providerService.getProviderById(id);
        model.addAttribute("provider", provider);
        return "provider_profile";  // Thymeleaf template for provider profile
    }

    // Upload new video
    @PostMapping("/uploadVideo")
    public String uploadVideo(@ModelAttribute Video video) {
        providerService.uploadVideo(video);
        return "redirect:/providers/" + video.getProvider().getId();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Provider> updateProvider(@PathVariable Long id, @RequestBody Provider providerDetails) {
        Provider updatedProvider = providerService.updateProvider(id, providerDetails);
        return ResponseEntity.ok(updatedProvider);
    }
}

