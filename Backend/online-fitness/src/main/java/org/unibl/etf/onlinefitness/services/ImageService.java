package org.unibl.etf.onlinefitness.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.unibl.etf.onlinefitness.models.dto.AvatarDTO;
import org.unibl.etf.onlinefitness.models.dto.ImageDTO;
import org.unibl.etf.onlinefitness.models.entities.AvatarEntity;
import org.unibl.etf.onlinefitness.models.entities.ImageEntity;
import org.unibl.etf.onlinefitness.repositories.ImageRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
@Transactional
public class ImageService {

    private ImageRepository imageRepository;
    private ModelMapper modelMapper;

    private File path;

    @PersistenceContext
    private EntityManager entityManager;

    public ImageService(ImageRepository imageRepository, ModelMapper modelMapper) {
        this.imageRepository = imageRepository;
        this.modelMapper = modelMapper;
        this.path = new File("./src/main/resources/images");
        if (!path.exists()) {
            path.mkdirs();
        }
    }

    public Integer uploadImage(MultipartFile file, Integer programId) throws IOException{
        var name = StringUtils.cleanPath(file.getOriginalFilename());
        var image = ImageDTO.builder().name(name).type(file.getContentType()).size(file.getSize()).build();
        image.setProgramId(programId);
        var imageEntity =  modelMapper.map(image, ImageEntity.class);
        imageRepository.saveAndFlush(imageEntity);
        entityManager.refresh(imageEntity);
        Files.write(Path.of(generatePath(imageEntity)), file.getBytes());
        return imageEntity.getId();
    }

    public ImageDTO downloadImage(Integer id) throws IOException {
        var image = imageRepository.findById(id).orElseThrow(IOException::new);
        var path = generatePath(image);
        var data = Files.readAllBytes(Path.of(path));
        var imageDTO = modelMapper.map(image, ImageDTO.class);
        imageDTO.setData(data);
        return imageDTO;
    }

    private String generatePath(ImageEntity image){
        var temp = image.getType().split("/");
        var name = image.getId() + "." + temp[1];
        var file = this.path + File.separator + name;
        System.out.println(file);
        return file;
    }
}
