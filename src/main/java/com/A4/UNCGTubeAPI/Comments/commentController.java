package com.A4.UNCGTubeAPI.Comments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/comments")
public class commentController {

    @Autowired
    private commentServ commentService;

    // Get all comments for a specific video by video ID
    @GetMapping("/video/{videoId}")
    public ResponseEntity<List<Comment>> getCommentsByVideo(@PathVariable Long videoId) {
        List<Comment> comments = commentService.getCommentsByVideoId(videoId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    // Add a new comment to a specific video
    @PostMapping("/video/{videoId}")
    public ResponseEntity<Comment> addComment(@PathVariable Long videoId, @RequestBody Comment comment) {
        try {
            Comment createdComment = commentService.addComment(videoId, comment);
            return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Add a reply to a comment
    @PostMapping("/reply/{commentId}")
    public ResponseEntity<Comment> replyToComment(@PathVariable Long commentId, @RequestBody Comment replyComment) {
        Comment reply = commentService.replyToComment(commentId, replyComment);
        return ResponseEntity.ok(reply);
    }

    // Update a comment by comment ID
    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long id, @RequestBody Comment updatedComment) {
        try {
            Comment updated = commentService.updateComment(id, updatedComment);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Delete a comment by comment ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        try {
            commentService.deleteComment(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
