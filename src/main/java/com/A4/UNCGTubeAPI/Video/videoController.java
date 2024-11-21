package com.A4.UNCGTubeAPI.Video;

import com.A4.UNCGTubeAPI.Provider.Provider;
import com.A4.UNCGTubeAPI.Provider.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/videos")
public class videoController {

    @Autowired
    private videoServ videoService;

    @Autowired
    private ProviderService providerService;


    @GetMapping("/provider/{id}")
    public String getProvider(@PathVariable Long id, Model model) {
        Provider provider = providerService.getProviderById(id);
        model.addAttribute("provider", provider);
        return "provider_profile";  // Thymeleaf template to display provider info
    }

    // Increment view count on video watch
    @GetMapping("/{id}")
    public String watchVideo(@PathVariable Long id) {
        videoService.incrementViewCount(id);
        return "redirect:/videos/watch/" + id;
    }

    @GetMapping("/upload/{providerId}")
    public String showUploadForm(@PathVariable Long providerId, Model model) {
        model.addAttribute("providerId", providerId); // Passing providerId to Thymeleaf
        return "upload_video"; // The HTML template
    }

    // Handle video upload
    @PostMapping("/upload/{providerId}")
    public String uploadVideo(@PathVariable Long providerId, @RequestParam("title") String title,
                              @RequestParam("description") String description, @RequestParam("videoFile") MultipartFile videoFile) {
        StorageService storageService = new StorageService();
        String videoUrl = storageService.storeVideo(videoFile); // Call service to store video
        Video video = new Video(title, description, videoUrl, 0); // Video object
        video.setProviderId(providerId); // Set the provider id
        videoService.saveVideo(video); // Save video to database
        return "redirect:/providers/" + providerId; // Redirect to provider's profile after upload
    }

}
