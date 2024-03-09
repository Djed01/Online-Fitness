package org.unibl.etf.onlinefitness.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.unibl.etf.onlinefitness.models.entities.MessageEntity;

import java.util.List;

public interface MessageRepository extends JpaRepository<MessageEntity,Integer> {
    List<MessageEntity> findAllBySenderIdOrReceiverId(Integer receiverId,Integer senderId);
}
