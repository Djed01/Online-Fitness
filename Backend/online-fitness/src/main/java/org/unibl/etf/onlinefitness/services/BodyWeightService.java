package org.unibl.etf.onlinefitness.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.unibl.etf.onlinefitness.models.dto.ActivityDTO;
import org.unibl.etf.onlinefitness.models.dto.BodyWeightDTO;
import org.unibl.etf.onlinefitness.models.entities.BodyWeightEntity;
import org.unibl.etf.onlinefitness.models.entities.UserEntity;
import org.unibl.etf.onlinefitness.repositories.BodyWeightRepository;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BodyWeightService {
    private final BodyWeightRepository bodyWeightRepository;
    private final ModelMapper modelMapper;

    public List<BodyWeightDTO> getAllBodyWeightsByUserId(Integer id){
        List<BodyWeightEntity> bodyWeight = bodyWeightRepository.findAllByUserId(id);
        return bodyWeight.stream()
                .map(weight -> modelMapper.map(weight, BodyWeightDTO.class))
                .collect(Collectors.toList());
    }

    public BodyWeightDTO addBodyWeight(BodyWeightDTO bodyWeightDTO,Integer id){
        BodyWeightEntity entity = modelMapper.map(bodyWeightDTO,BodyWeightEntity.class);
        UserEntity userEntity = new UserEntity();
        userEntity.setId(id);
        entity.setUser(userEntity);
        entity.setDate(new Date(System.currentTimeMillis()));
        entity = bodyWeightRepository.save(entity);
        return modelMapper.map(entity,BodyWeightDTO.class);
    }
}
