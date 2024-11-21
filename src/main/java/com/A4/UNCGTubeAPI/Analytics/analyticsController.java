package com.A4.UNCGTubeAPI.Analytics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/analytics")
public class analyticsController {

    @Autowired
    private analyticsServ analyticsService;

    // Get analytics for a specific video by ID
    @GetMapping("/videos/{videoId}")
    public String getAnalyticsByVideoId(@PathVariable Long videoId, Model model) {
        Analytics analytics = analyticsService.getAnalyticsByVideoId(videoId);

        // Add both video and analytics to the model
        model.addAttribute("analytics", analytics);
        model.addAttribute("video", analytics.getVideo()); // Get associated video from analytics

        return "analytics/view"; // Return the Thymeleaf template name
    }

    // Show form to update analytics (views, comments, subscribers)
    @GetMapping("/videos/{videoId}/edit")
    public String showUpdateForm(@PathVariable Long videoId, Model model) {
        Analytics analytics = analyticsService.getAnalyticsByVideoId(videoId);
        model.addAttribute("analytics", analytics); // Pre-populate the form with current analytics
        return "analytics/edit"; // Thymeleaf form to edit analytics
    }

    // Update analytics for a video (views, comments, and subscribers)
    @PostMapping("/videos/{videoId}/edit")
    public String updateAnalytics(@PathVariable Long videoId,
                                  @RequestParam Long viewsCount,
                                  @RequestParam Long commentCount,
                                  @RequestParam Long subscriberCount,
                                  Model model) {
        Analytics updatedAnalytics = analyticsService.updateAnalytics(videoId, viewsCount, commentCount, subscriberCount);
        model.addAttribute("analytics", updatedAnalytics); // Add updated analytics to model
        return "redirect:/analytics/videos/" + videoId; // Redirect back to the analytics view page
    }

    // Increment view count for a video
    @PostMapping("/videos/{videoId}/incrementViews")
    public String incrementViews(@PathVariable Long videoId) {
        analyticsService.incrementViews(videoId);
        return "redirect:/analytics/videos/" + videoId; // Redirect back to the video analytics page
    }

    // Increment comment count for a video
    @PostMapping("/videos/{videoId}/incrementComments")
    public String incrementComments(@PathVariable Long videoId) {
        analyticsService.incrementComments(videoId);
        return "redirect:/analytics/videos/" + videoId; // Redirect back to the video analytics page
    }

    // Increment subscriber count for a video
    @PostMapping("/videos/{videoId}/incrementSubscribers")
    public String incrementSubscribers(@PathVariable Long videoId, @RequestParam Long newSubscribers) {
        analyticsService.incrementSubscribers(videoId, newSubscribers);
        return "redirect:/analytics/videos/" + videoId; // Redirect back to the video analytics page
    }

    // Delete analytics for a video
    @PostMapping("/videos/{videoId}/delete")
    public String deleteAnalytics(@PathVariable Long videoId) {
        analyticsService.deleteAnalytics(videoId);
        return "redirect:/"; // Redirect to the home page after deleting
    }
}
