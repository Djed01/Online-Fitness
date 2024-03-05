package org.unibl.etf.onlinefitness.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.unibl.etf.onlinefitness.models.dto.AttributeDTO;
import org.unibl.etf.onlinefitness.models.dto.ProgramDTO;
import org.unibl.etf.onlinefitness.models.entities.CategoryAttributeEntity;
import org.unibl.etf.onlinefitness.models.entities.ProgramEntity;
import org.unibl.etf.onlinefitness.repositories.ProgramRepository;

import java.util.List;
import java.util.Optional;
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

    public ProgramDTO findProgramByProgramId(Integer id){
        ProgramEntity entity = programRepository.findAllById(id);
        return modelMapper.map(entity, ProgramDTO.class);
    }


    public List<ProgramDTO> findAllByUserId(Integer id){
        List<ProgramEntity> entities = programRepository.findAllByUserId(id);
        return entities.stream()
                .map(entity -> modelMapper.map(entity, ProgramDTO.class))
                .collect(Collectors.toList());
    }

    public ProgramDTO addProgram(ProgramDTO programDTO) {
        ProgramEntity programEntity = modelMapper.map(programDTO, ProgramEntity.class);
        programEntity.setStatus(true);
        programEntity = programRepository.save(programEntity);


        return modelMapper.map(programEntity, ProgramDTO.class);
    }

    public ProgramDTO changeProgramStatus(Integer id, Boolean status) {
        Optional<ProgramEntity> optionalProgramEntity = programRepository.findById(id);
        if (optionalProgramEntity.isPresent()) {
            ProgramEntity programEntity = optionalProgramEntity.get();
            programEntity.setStatus(status);
            programRepository.save(programEntity);
            return modelMapper.map(programEntity, ProgramDTO.class);
        } else {
            //Program not found
            return null;
        }
    }

    public void deleteProgram(Integer id) {
        programRepository.deleteById(id);
    }

}
