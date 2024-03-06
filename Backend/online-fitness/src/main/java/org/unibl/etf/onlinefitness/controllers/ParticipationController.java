package org.unibl.etf.onlinefitness.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.unibl.etf.onlinefitness.models.dto.ParticipationDTO;
import org.unibl.etf.onlinefitness.models.dto.ProgramDTO;
import org.unibl.etf.onlinefitness.services.ParticipationService;

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

    @GetMapping
    public Boolean participates(@RequestParam Integer programId,@RequestParam Integer userId){
        return this.participationService.participates(programId,userId);
    }
}
