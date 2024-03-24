package org.unibl.etf.onlinefitness.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.unibl.etf.onlinefitness.models.dto.MessageDTO;
import org.unibl.etf.onlinefitness.models.dto.SubscriptionDTO;
import org.unibl.etf.onlinefitness.models.entities.CategoryEntity;
import org.unibl.etf.onlinefitness.models.entities.ProgramEntity;
import org.unibl.etf.onlinefitness.models.entities.SubscriptionEntity;
import org.unibl.etf.onlinefitness.models.entities.UserEntity;
import org.unibl.etf.onlinefitness.repositories.CategoryRepository;
import org.unibl.etf.onlinefitness.repositories.ProgramRepository;
import org.unibl.etf.onlinefitness.repositories.SubscriptionRepository;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final CategoryRepository categoryRepository;
    private final ProgramRepository programRepository;
    private final EmailService emailService;
    private final ModelMapper modelMapper;

    public List<SubscriptionDTO> getAllByUserId(Integer userId) {
        List<CategoryEntity> categories = categoryRepository.findAllByStatus(true);
        Map<Integer, Boolean> subscriptionMap = getSubscriptionMap(userId);

        List<SubscriptionDTO> subscriptions = new ArrayList<>();
        for (CategoryEntity category : categories) {
            Boolean subscribed = subscriptionMap.getOrDefault(category.getId(), false);
            SubscriptionDTO dto = new SubscriptionDTO();
            dto.setCategory(category.getName());
            dto.setSubscribed(subscribed);
            dto.setId(category.getId());
            subscriptions.add(dto);
        }

        return subscriptions;
    }

    private Map<Integer, Boolean> getSubscriptionMap(Integer userId) {
        List<SubscriptionEntity> subscriptions = subscriptionRepository.findAllByUserId(userId);
        Map<Integer, Boolean> subscriptionMap = new HashMap<>();
        for (SubscriptionEntity subscription : subscriptions) {
            subscriptionMap.put(subscription.getCategory().getId(), true);
        }
        return subscriptionMap;
    }

    public void subscribeOrUnsubscribe(Integer categoryId,Integer userId){
        SubscriptionEntity entity = subscriptionRepository.findByCategoryIdAndUserId(categoryId,userId);
        if(entity != null){
            subscriptionRepository.delete(entity);
        }else{
            SubscriptionEntity subscriptionEntity = new SubscriptionEntity();
            CategoryEntity category = new CategoryEntity();
            category.setId(categoryId);
            subscriptionEntity.setCategory(category);
            UserEntity userEntity = new UserEntity();
            userEntity.setId(userId);
            subscriptionEntity.setUser(userEntity);
            this.subscriptionRepository.save(subscriptionEntity);
        }
    }

    @Scheduled(cron = "0 44 17 * * *")
    public void sendNewsletterEmail(){
        List<SubscriptionEntity> subscriptions = subscriptionRepository.findAll();
        LocalDateTime currentTime = LocalDateTime.now();
        var currentDate= Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant());
        LocalDateTime yesterdayTime=currentTime.minusDays(1);
        var yesterdayDate=Date.from(yesterdayTime.atZone(ZoneId.systemDefault()).toInstant());
        for(SubscriptionEntity subscription : subscriptions){
            List<ProgramEntity> createdPrograms = this.programRepository.findByCategoryIdAndCreationDateBetween(subscription.getCategory().getId(),yesterdayDate,currentDate);
            if(!createdPrograms.isEmpty()){
                StringBuilder stringBuilder = new StringBuilder("Checkout newest programs in "+subscription.getCategory().getName()+" category!\n");
                for(ProgramEntity program:createdPrograms){
                    stringBuilder.append(program.getTitle()+"\n");
                }
                this.emailService.sendNewsletterEmail(stringBuilder.toString(),subscription.getUser().getEmail());
            }
        }
    }
}
