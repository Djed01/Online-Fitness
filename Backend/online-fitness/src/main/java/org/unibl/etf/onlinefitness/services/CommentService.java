package org.unibl.etf.onlinefitness.services;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.unibl.etf.onlinefitness.models.dto.CommentDTO;
import org.unibl.etf.onlinefitness.models.dto.ProgramDTO;
import org.unibl.etf.onlinefitness.models.entities.CommentEntity;
import org.unibl.etf.onlinefitness.models.entities.ProgramEntity;
import org.unibl.etf.onlinefitness.repositories.CommentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    private CommentRepository commentRepository;
    private ModelMapper modelMapper;

    public CommentService(CommentRepository commentRepository, ModelMapper modelMapper){
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;
    }

    public List<CommentDTO> findAllComments() {
        List<CommentEntity> entities = commentRepository.findAll();
        return entities.stream()
                .map(entity -> modelMapper.map(entity, CommentDTO.class))
                .collect(Collectors.toList());
    }

    public List<CommentDTO> findAllByProgramId(Integer id){
        List<CommentEntity> entities = commentRepository.findAllByProgramId(id);
        return  entities.stream()
                .map(entity -> modelMapper.map(entity,CommentDTO.class))
                .collect(Collectors.toList());
    }

    public CommentDTO addComment(CommentDTO commentDTO) {
        CommentEntity commentEntity = modelMapper.map(commentDTO, CommentEntity.class);
        commentRepository.save(commentEntity);
        return modelMapper.map(commentEntity, CommentDTO.class);
    }

    public void deleteComment(Integer id) {
        commentRepository.deleteById(id);
    }


}
