package org.unibl.etf.onlinefitness.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.unibl.etf.onlinefitness.services.ImageService;

import java.awt.*;
import java.io.IOException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/image")
public class ImageController {
    private final ImageService imageService;

    public ImageController(ImageService imageService){
        this.imageService = imageService;
    }

    @PostMapping("/{programId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Integer uploadImage(@RequestParam("image") MultipartFile file, @PathVariable Integer programId) throws IOException {
        return this.imageService.uploadImage(file,programId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> downloadImage(@PathVariable Integer id) throws IOException{
        var avatar = imageService.downloadImage(id);
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(avatar.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + avatar.getName() + "\"")
                .body(avatar.getData());
    }

}
