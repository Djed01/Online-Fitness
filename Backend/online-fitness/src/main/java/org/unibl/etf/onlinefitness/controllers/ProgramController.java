package org.unibl.etf.onlinefitness.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.unibl.etf.onlinefitness.models.dto.ProgramDTO;
import org.unibl.etf.onlinefitness.models.entities.ProgramEntity;
import org.unibl.etf.onlinefitness.services.ProgramService;

import java.util.List;

@RestController
@RequestMapping("api/program")
public class ProgramController {
    private ProgramService programService;

    public ProgramController(ProgramService programService){
        this.programService = programService;
    }

    @GetMapping
    public List<ProgramDTO> findAll(){
        return this.programService.findAll();
    }
}
