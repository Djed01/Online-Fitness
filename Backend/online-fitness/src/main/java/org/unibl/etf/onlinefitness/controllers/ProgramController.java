package org.unibl.etf.onlinefitness.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.unibl.etf.onlinefitness.models.dto.ProgramDTO;
import org.unibl.etf.onlinefitness.models.entities.ProgramEntity;
import org.unibl.etf.onlinefitness.services.ProgramService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/program")
public class ProgramController {
    private ProgramService programService;

    public ProgramController(ProgramService programService){
        this.programService = programService;
    }

    @GetMapping
    @CrossOrigin(origins = "http://localhost:4200")
    public List<ProgramDTO> findAll(){
        return this.programService.findAll();
    }

    @GetMapping("/user/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<ProgramDTO> findAllByUserId(@PathVariable Integer id){
        return this.programService.findAllByUserId(id);
    }

    @PostMapping
    @CrossOrigin(origins = "http://localhost:4200")
    public ProgramDTO addProgram(@RequestBody ProgramDTO programDTO) {
        return this.programService.addProgram(programDTO);
    }

    @PutMapping("/{id}/status")
    @CrossOrigin(origins = "http://localhost:4200")
    public ProgramDTO changeProgramStatus(@PathVariable Integer id, @RequestParam Boolean status) {
        return this.programService.changeProgramStatus(id, status);
    }

    @DeleteMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Void> deleteProgram(@PathVariable Integer id) {
        this.programService.deleteProgram(id);
        return ResponseEntity.ok().build();
    }
}
