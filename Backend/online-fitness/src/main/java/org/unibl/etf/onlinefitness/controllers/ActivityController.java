package org.unibl.etf.onlinefitness.controllers;

import com.sun.tools.jconsole.JConsoleContext;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.unibl.etf.onlinefitness.models.dto.ActivityDTO;
import org.unibl.etf.onlinefitness.models.dto.CommentDTO;
import org.unibl.etf.onlinefitness.services.ActivityService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/activity")
public class ActivityController {
    private final ActivityService activityService;

    @GetMapping("/{id}")
    public List<ActivityDTO> getAllActivitiesByUserId(@PathVariable Integer id) {
        return activityService.getAllActivityByUserId(id);
    }

    @PostMapping("/{id}")
    public ActivityDTO addActivity(@RequestBody ActivityDTO activityDTO,@PathVariable Integer id){
        return activityService.addActivity(activityDTO,id);
    }

    @DeleteMapping("/{id}")
    public void deleteActivity(@PathVariable Integer id){
        try {
            activityService.deleteActivity(id);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
