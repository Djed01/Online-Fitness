package org.unibl.etf.onlinefitness.controllers;

import com.sun.tools.jconsole.JConsoleContext;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.unibl.etf.onlinefitness.models.dto.ActivityDTO;
import org.unibl.etf.onlinefitness.models.dto.CommentDTO;
import org.unibl.etf.onlinefitness.models.dto.PdfDTO;
import org.unibl.etf.onlinefitness.models.enumeration.LogType;
import org.unibl.etf.onlinefitness.services.ActivityService;
import org.unibl.etf.onlinefitness.services.LogService;
import org.unibl.etf.onlinefitness.services.PdfService;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/activity")
public class ActivityController {
    private final ActivityService activityService;
    private final PdfService pdfService;
    private final LogService logService;

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
            logService.log(LogType.ERROR,"Error occurred while deleting activity!");
            e.printStackTrace();
        }
    }

    @GetMapping("/pdf/{id}")
    public ResponseEntity<?> downloadPdf(@PathVariable Integer id) throws IOException {
        PdfDTO pdfDTO = pdfService.generatePDFByUserId(id);
        return ResponseEntity.ok().contentType(MediaType.parseMediaType("application/pdf"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + pdfDTO.getFileName()+".pdf" + "\"")
                .body(pdfDTO.getData());
    }
}
