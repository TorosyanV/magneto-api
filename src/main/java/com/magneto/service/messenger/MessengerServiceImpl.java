package com.magneto.service.messenger;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.magneto.data.dataobject.ChaterDetails;
import com.magneto.data.dataobject.Message;
import com.magneto.data.repository.ConversationRepository;
import com.magneto.data.repository.MessengerRepository;
import com.magneto.service.messenger.dto.ChaterDetailsDto;
import com.magneto.service.messenger.dto.MessageDto;
import com.magneto.service.messenger.dto.StandardMessageDto;
import com.magneto.util.Pagination;

/**
 * Created by vazgen on 2/20/16.
 */

@Service("messengerService")
@Transactional
public class MessengerServiceImpl implements MessengerService {

	@Autowired
	private ConversationRepository conversationRepository;

	@Autowired
	private MessengerRepository messengerRepository;

	@Override
	public void sendMessageToUser(StandardMessageDto message, int senderId, int recipientId) {
		conversationRepository.sendMessage(senderId, recipientId, message.getText());
	}

	@Override
	public List<ChaterDetailsDto> getChaters(int userId) {
		List<ChaterDetails> chaters = messengerRepository.getChaters(userId);
		List<ChaterDetailsDto> chatersDto = new ArrayList<>();
		for (ChaterDetails ch : chaters) {
			ChaterDetailsDto chater = new ChaterDetailsDto();
			chater.setId(ch.getId());
			chater.setEmail(ch.getEmail());
			chater.setName(ch.getName());
			chatersDto.add(chater);
		}
		return chatersDto;
	}

	@Override
	public List<MessageDto> getMessages(int userOne, int userTwo, Pagination pagination) {
		List<Message> messages = messengerRepository.getMessages(userOne, userTwo,pagination);
		List<MessageDto> messagesDto = new ArrayList<>();
		for (Message msg : messages) {
			MessageDto message = new MessageDto();
			message.setText(msg.getText());
			message.setMine(msg.getSenderId() == userOne ? true : false);
			message.setSeen(msg.isSeen());
			message.setDate(msg.getDate());
			messagesDto.add(message);
		}
		return messagesDto;

	}
}
