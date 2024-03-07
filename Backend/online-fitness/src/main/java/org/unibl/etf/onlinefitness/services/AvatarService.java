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
import org.unibl.etf.onlinefitness.models.entities.AvatarEntity;
import org.unibl.etf.onlinefitness.repositories.AvatarRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
@Transactional
public class AvatarService {

    private AvatarRepository avatarRepository;
    private ModelMapper modelMapper;
    private File path;

    @Autowired
    UserService userService;

    @PersistenceContext
    private EntityManager entityManager;

    public AvatarService(AvatarRepository avatarRepository, ModelMapper modelMapper){
        this.avatarRepository = avatarRepository;
        this.modelMapper = modelMapper;
        this.path = new File("./src/main/resources/avatars");
        if (!path.exists()) {
            path.mkdirs();
        }
    }

    public Integer uploadAvatar(MultipartFile file,Integer userId) throws IOException {
        var name = StringUtils.cleanPath(file.getOriginalFilename());
        var avatar = AvatarDTO.builder().name(name).type(file.getContentType()).size(file.getSize()).build();
        avatar.setUserId(userId);
        var avatarEntity =  modelMapper.map(avatar, AvatarEntity.class);
        avatarRepository.saveAndFlush(avatarEntity);
        entityManager.refresh(avatarEntity);//dobio sam id od baze sada cuvamo na fajl sistemu
        Files.write(Path.of(generatePath(avatarEntity)), file.getBytes());
        return avatarEntity.getId();
    }

    public Integer updateAvatar(MultipartFile file,Integer userId) throws IOException {
        var name = StringUtils.cleanPath(file.getOriginalFilename());
        var avatar = AvatarDTO.builder().name(name).type(file.getContentType()).size(file.getSize()).build();
        avatar.setUserId(userId);
        var avatarEntity =  modelMapper.map(avatar, AvatarEntity.class);

        //Delete if exists
        AvatarEntity oldAvatar = avatarRepository.findByUserId(userId);
        if(oldAvatar != null){
            avatarRepository.delete(oldAvatar);
            var path = generatePath(oldAvatar);
            File oldFile = new File(path);
            oldFile.delete();
        }

        avatarRepository.saveAndFlush(avatarEntity);
        entityManager.refresh(avatarEntity);
        Files.write(Path.of(generatePath(avatarEntity)), file.getBytes());
        return avatarEntity.getId();
    }

    public AvatarDTO downloadAvatar(Integer id) throws IOException {
        var image = avatarRepository.findById(id).orElseThrow(IOException::new);
        var path = generatePath(image);
        var data = Files.readAllBytes(Path.of(path));
        var avatarDTO = modelMapper.map(image, AvatarDTO.class);
        avatarDTO.setData(data);
        return avatarDTO;
    }

    private String generatePath(AvatarEntity avatar){
        var temp = avatar.getType().split("/");
        var name = avatar.getId() + "." + temp[1];
        var file = this.path + File.separator + name;
        System.out.println(file);
        return file;
    }

}
