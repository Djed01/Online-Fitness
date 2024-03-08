package org.unibl.etf.onlinefitness.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.unibl.etf.onlinefitness.models.dto.QuestionDTO;
import org.unibl.etf.onlinefitness.models.entities.ProgramEntity;
import org.unibl.etf.onlinefitness.models.entities.QuestionEntity;
import org.unibl.etf.onlinefitness.models.entities.UserEntity;
import org.unibl.etf.onlinefitness.repositories.QuestionRepository;

import java.sql.Date;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final ModelMapper modelMapper;

    public QuestionDTO addQuestion(QuestionDTO questionDTO){
        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setContent(questionDTO.getContent());
        questionEntity.setDate(questionDTO.getDate());
        questionEntity.setSeen(questionDTO.getSeen());

        UserEntity userEntity = new UserEntity();
        userEntity.setId(questionDTO.getUserId());
        questionEntity.setUser(userEntity);

        ProgramEntity programEntity = new ProgramEntity();
        programEntity.setId(questionDTO.getProgramId());
        questionEntity.setProgram(programEntity);

        questionEntity.setSeen(false);

        questionRepository.save(questionEntity);

        return modelMapper.map(questionEntity,QuestionDTO.class);
    }

}
