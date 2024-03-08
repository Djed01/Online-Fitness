package org.unibl.etf.onlinefitness.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.unibl.etf.onlinefitness.models.dto.QuestionDTO;
import org.unibl.etf.onlinefitness.services.QuestionService;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/question")
public class QuestionController {
    private final QuestionService questionService;

    @PostMapping
    public QuestionDTO addQuestion(@RequestBody QuestionDTO questionDTO){
        return questionService.addQuestion(questionDTO);
    }
}
