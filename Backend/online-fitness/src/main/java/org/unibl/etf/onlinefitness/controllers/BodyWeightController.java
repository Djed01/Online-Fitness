package org.unibl.etf.onlinefitness.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.unibl.etf.onlinefitness.models.dto.BodyWeightDTO;
import org.unibl.etf.onlinefitness.services.BodyWeightService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/body-weight")
public class BodyWeightController {
    private final BodyWeightService bodyWeightService;

    @GetMapping("/{id}")
    public List<BodyWeightDTO> getAllByUserId(@PathVariable Integer id){
        return bodyWeightService.getAllBodyWeightsByUserId(id);
    }

    @PostMapping
    public BodyWeightDTO addBodyWeight(@RequestBody BodyWeightDTO bodyWeightDTO){
        return bodyWeightService.addBodyWeight(bodyWeightDTO);
    }
}
