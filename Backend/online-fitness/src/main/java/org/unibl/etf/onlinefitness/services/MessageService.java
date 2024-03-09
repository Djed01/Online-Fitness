package org.unibl.etf.onlinefitness.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.unibl.etf.onlinefitness.models.dto.MessageDTO;
import org.unibl.etf.onlinefitness.models.dto.MessageRequestDTO;
import org.unibl.etf.onlinefitness.models.entities.MessageEntity;
import org.unibl.etf.onlinefitness.models.entities.UserEntity;
import org.unibl.etf.onlinefitness.repositories.MessageRepository;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
    private final ModelMapper modelMapper;

    public MessageDTO sendMessage(MessageRequestDTO messageRequestDTO){
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setContent(messageRequestDTO.getContent());
        messageEntity.setDate(new Date());
        UserEntity sender = new UserEntity();
        sender.setId(messageRequestDTO.getSenderId());
        messageEntity.setSender(sender);
        UserEntity receiver = new UserEntity();
        receiver.setId(messageRequestDTO.getReceiverId());
        messageEntity.setReceiver(receiver);
        messageRepository.save(messageEntity);
        return modelMapper.map(messageEntity,MessageDTO.class);
    }

    public List<MessageDTO> findUserMessages(Integer id){
        return this.messageRepository.findAllBySenderIdOrReceiverId(id,id).stream()
                .map(message -> modelMapper.map(message, MessageDTO.class))
                .collect(Collectors.toList());
    }

}
