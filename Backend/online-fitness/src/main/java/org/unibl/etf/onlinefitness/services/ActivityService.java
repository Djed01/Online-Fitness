package org.unibl.etf.onlinefitness.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.unibl.etf.onlinefitness.models.dto.ActivityDTO;
import org.unibl.etf.onlinefitness.models.dto.MessageDTO;
import org.unibl.etf.onlinefitness.models.entities.ActivityEntity;
import org.unibl.etf.onlinefitness.models.entities.UserEntity;
import org.unibl.etf.onlinefitness.models.enumeration.LogType;
import org.unibl.etf.onlinefitness.repositories.ActivityRepository;


import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActivityService {

    private final ActivityRepository activityRepository;
    private final ModelMapper modelMapper;
    private final LogService logService;

    public List<ActivityDTO> getAllActivityByUserId(Integer id){
        List<ActivityEntity> entities = activityRepository.findAllByUserIdAndStatus(id,true);
        return  entities.stream()
                .map(activity -> modelMapper.map(activity, ActivityDTO.class))
                .collect(Collectors.toList());
    }

    public ActivityDTO addActivity(ActivityDTO activityDTO,Integer userId){
        ActivityEntity entity = modelMapper.map(activityDTO,ActivityEntity.class);
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        entity.setUser(userEntity);
        entity.setStatus(true);
        entity.setDate(new Date());
        return modelMapper.map(activityRepository.save(entity),ActivityDTO.class);
    }

    public void deleteActivity(Integer id) throws Exception{
        Optional<ActivityEntity> optionalEntity = activityRepository.findById(id);
        if (optionalEntity.isPresent()) {
            ActivityEntity entity = optionalEntity.get();
            entity.setStatus(false);
            activityRepository.save(entity);
        }else{
            logService.log(LogType.INFO,"No activity with provided ID!");
            throw new Exception("No activity with provided ID!");
        }
    }
}
