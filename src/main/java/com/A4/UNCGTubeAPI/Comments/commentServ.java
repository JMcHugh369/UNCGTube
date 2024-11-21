package com.A4.UNCGTubeAPI.Comments;

import com.A4.UNCGTubeAPI.Provider.Provider;
import com.A4.UNCGTubeAPI.Provider.ProviderRepo;
import com.A4.UNCGTubeAPI.Video.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class commentServ {

    @Autowired
    private ProviderRepo providerRepo;


    @Autowired
    private commentRepo commentRepo;

    @Autowired
    private com.A4.UNCGTubeAPI.Video.videoRepo videoRepo;

    // Get all comments for a specific video
    public List<Comment> getCommentsByVideoId(Long videoId) {
        return commentRepo.findByVideoId(videoId);
    }

    // Add a new comment for a video
    public Comment addComment(Long videoId, Comment comment) {
        Video video = videoRepo.findById(videoId)
                .orElseThrow(() -> new IllegalArgumentException("Video not found with ID: " + videoId));

        comment.setVideo(video); // Associate the comment with the fetched video
        return commentRepo.save(comment);
    }

    // Add a reply to a comment
    public Comment addReply(Long commentId, Comment reply) {
        Comment parentComment = commentRepo.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Comment not found with ID: " + commentId));

        reply.setParentComment(parentComment); // Set the parent comment for the reply
        reply.setVideo(parentComment.getVideo()); // Ensure the reply is associated with the same video
        return commentRepo.save(reply);
    }

    // Update a comment by ID
    public Comment updateComment(Long id, Comment updatedComment) {
        return commentRepo.findById(id).map(comment -> {
            comment.setContent(updatedComment.getContent());
            return commentRepo.save(comment);
        }).orElseThrow(() -> new IllegalArgumentException("Comment not found with ID: " + id));
    }

    // Delete a comment by ID
    public void deleteComment(Long id) {
        Comment comment = commentRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Comment not found with ID: " + id));
        commentRepo.delete(comment);
    }

    public Comment replyToComment(Long commentId, Comment replyComment) {
        // Find the original comment to which the reply will be added
        Comment originalComment = commentRepo.findById(commentId).orElseThrow(() -> new RuntimeException("Comment not found"));

        // Set the video for the reply to be the same as the original comment's video
        Video video = originalComment.getVideo();
        replyComment.setVideo(video);

        // Set the provider (influencer) for the reply, assuming you have the provider information available
        Provider provider = providerRepo.findById(replyComment.getProvider().getId())
                .orElseThrow(() -> new RuntimeException("Provider not found"));
        replyComment.setProvider(provider);

        // Optionally, set the "parent" comment to create a relationship between the original and reply
        replyComment.setParentComment(originalComment);

        // Save the reply to the database
        return commentRepo.save(replyComment);
    }

}
