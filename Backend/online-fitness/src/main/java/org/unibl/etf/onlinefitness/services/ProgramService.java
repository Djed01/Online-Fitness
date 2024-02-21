package org.unibl.etf.onlinefitness.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unibl.etf.onlinefitness.models.dto.ProgramDTO;
import org.unibl.etf.onlinefitness.models.entities.ProgramEntity;
import org.unibl.etf.onlinefitness.repositories.ProgramRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProgramService {

    private ProgramRepository programRepository;

    private ModelMapper modelMapper;

    public ProgramService(ProgramRepository programRepository, ModelMapper modelMapper){
        this.programRepository = programRepository;
        this.modelMapper = modelMapper;
    }

    public List<ProgramDTO> findAll(){
        List<ProgramEntity> entities = programRepository.findAll();
        return entities.stream()
                .map(entity -> modelMapper.map(entity, ProgramDTO.class))
                .collect(Collectors.toList());
    }

}
