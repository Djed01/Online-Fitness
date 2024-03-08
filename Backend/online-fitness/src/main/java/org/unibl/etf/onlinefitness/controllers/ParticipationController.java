package org.unibl.etf.onlinefitness.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.unibl.etf.onlinefitness.models.dto.ParticipationDTO;
import org.unibl.etf.onlinefitness.models.dto.ProgramDTO;
import org.unibl.etf.onlinefitness.models.entities.ParticipationEntity;
import org.unibl.etf.onlinefitness.services.ParticipationService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/participation")
@RequiredArgsConstructor
public class ParticipationController {

    private final ParticipationService participationService;

    @PostMapping
    public ParticipationDTO addProgram(@RequestBody ParticipationDTO participationDTO) {
        return this.participationService.addParticipation(participationDTO);
    }

    @GetMapping("/{userId}")
    public List<ParticipationDTO> findAllByUserId(@PathVariable Integer userId){
        return this.participationService.findAllByUserId(userId);
    }

    @GetMapping
    public Boolean participates(@RequestParam Integer programId,@RequestParam Integer userId){
        return this.participationService.participates(programId,userId);
    }
}
