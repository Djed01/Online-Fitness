package org.unibl.etf.onlinefitness.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.unibl.etf.onlinefitness.models.entities.CategoryEntity;
import org.unibl.etf.onlinefitness.repositories.CategoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<CategoryEntity> findAll(){
        return categoryRepository.findAll();
    }
}
