package com.magneto.service.messenger;

import java.util.List;

import com.magneto.service.messenger.dto.ChaterDetailsDto;
import com.magneto.service.messenger.dto.MessageDto;
import com.magneto.service.messenger.dto.StandardMessageDto;
import com.magneto.util.Pagination;

/**
 * Created by vazgen on 2/20/16.
 */
public interface MessengerService {
    void sendMessageToUser(StandardMessageDto message, int senderId, int recipientId);
    List<ChaterDetailsDto> getChaters(int userId);
    List<MessageDto> getMessages(int userOne, int userTwo,Pagination pagination);
}