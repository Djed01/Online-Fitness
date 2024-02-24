package org.unibl.etf.onlinefitness.controllers;

import org.springframework.web.bind.annotation.*;
import org.unibl.etf.onlinefitness.models.dto.CommentDTO;
import org.unibl.etf.onlinefitness.services.CommentService;


import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/comment")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    @GetMapping
    public List<CommentDTO> findAllComments() {
        return commentService.findAllComments();
    }

    @GetMapping("/program/{id}")
    public List<CommentDTO> findAllCommentsByProgramId(@PathVariable Integer id) {
        return commentService.findAllByProgramId(id);
    }

    @PostMapping
    public CommentDTO addComment(@RequestBody CommentDTO commentDTO) {
        return commentService.addComment(commentDTO);
    }


    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable Integer id) {
        commentService.deleteComment(id);
    }

}
