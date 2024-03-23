package org.unibl.etf.onlinefitness.services;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.unibl.etf.onlinefitness.models.dto.AttributeDTO;
import org.unibl.etf.onlinefitness.models.dto.ProgramDTO;
import org.unibl.etf.onlinefitness.models.entities.CategoryAttributeEntity;
import org.unibl.etf.onlinefitness.models.entities.ProgramEntity;
import org.unibl.etf.onlinefitness.models.enumeration.LogType;
import org.unibl.etf.onlinefitness.repositories.ProgramRepository;

import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProgramService {

    private ProgramRepository programRepository;

    private ModelMapper modelMapper;
    private LogService logService;

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

    public List<ProgramDTO> findAllByStatus(){
        List<ProgramEntity> entities = programRepository.findAllByStatus(true);
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
            logService.log(LogType.INFO,"No log service with the provided id found!");
            //Program not found
            return null;
        }
    }

    @Scheduled(cron = "0 0 0 * * ?") // Executes at midnight every day
    public void updateProgramStatus() {
        List<ProgramEntity> programs = programRepository.findAllByStatus(true);
        LocalDate today = LocalDate.now();
        LocalDate fifteenDaysAgo = today.minusDays(15);

        for (ProgramEntity program : programs) {
            LocalDate creationDate = program.getCreationDate().toLocalDate();
            if (creationDate.isBefore(fifteenDaysAgo)) {
                program.setStatus(false);
                programRepository.save(program);
            }
        }
    }

    public void deleteProgram(Integer id) {
         programRepository.deleteById(id);
    }

}
