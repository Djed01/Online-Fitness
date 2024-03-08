package org.unibl.etf.onlinefitness.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.unibl.etf.onlinefitness.models.dto.CommentDTO;
import org.unibl.etf.onlinefitness.models.dto.ParticipationDTO;
import org.unibl.etf.onlinefitness.models.entities.CommentEntity;
import org.unibl.etf.onlinefitness.models.entities.ParticipationEntity;
import org.unibl.etf.onlinefitness.repositories.ParticipationRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ParticipationService {
    private final ParticipationRepository participationRepository;
    private final ModelMapper modelMapper;
    public ParticipationDTO addParticipation(ParticipationDTO participationDTO){
        ParticipationEntity participationEntity = modelMapper.map(participationDTO,ParticipationEntity.class);
        participationRepository.save(participationEntity);
        return modelMapper.map(participationEntity,ParticipationDTO.class);
    }

    public List<ParticipationDTO> findAllByUserId(Integer id){

        return participationRepository.findAllByUserId(id)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private ParticipationDTO convertToDto(ParticipationEntity participationEntity) {
        return modelMapper.map(participationEntity, ParticipationDTO.class);
    }

    public Boolean participates(Integer programId, Integer userId) {
        ParticipationEntity participationEntity = participationRepository.findByProgramIdAndUserId(programId, userId);
        return participationEntity != null;
    }
}
