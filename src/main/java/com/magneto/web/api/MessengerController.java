package com.magneto.web.api;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import com.magneto.data.entity.UserEntity;
import com.magneto.service.messenger.dto.MessagRecived;
import com.magneto.service.user.UserService;
import com.magneto.web.viewmodel.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import com.magneto.dto.User;
import com.magneto.service.messenger.MessengerService;
import com.magneto.service.messenger.dto.ChaterDetailsDto;
import com.magneto.service.messenger.dto.MessageDto;
import com.magneto.service.messenger.dto.StandardMessageDto;
import com.magneto.util.Pagination;
import com.magneto.web.viewmodel.messenger.UserMessage;

/**
 * Created by vazgen on 2/20/16.
 */
@RestController
@RequestMapping({"/messenger", "/api/messenger"})
public class MessengerController extends AuthorizedController {

    @Autowired
    private MessengerService messengerService;
    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private UserService userService;

    @PostMapping("/sendtouser")
    public void sendMessage(@RequestBody UserMessage message) {

        User user = super.getUser();

        StandardMessageDto messageDto = new StandardMessageDto();

        messageDto.setText(message.getText());
        messengerService.sendMessageToUser(messageDto, user.getId(), message.getToUserId());
    }

    @GetMapping("/getchaters")
    public List<ChaterDetailsDto> getChaters() {

        User user = super.getUser();

        return messengerService.getChaters(user.getId());
    }

    @GetMapping("/messages/{userTwo}/{pageNumber}/{pageSize}")
    public List<MessageDto> getMessages(@PathVariable int userTwo, @PathVariable int pageNumber,
                                        @PathVariable int pageSize) {

        User user = super.getUser();
        if (pageSize < 0)
            pageSize = 10;
        if (pageSize == 0)
            pageSize = 10;
        if (pageNumber <= 0)
            pageNumber = 1;
        Pagination pgn = new Pagination();
        pgn.setPageNumber(pageNumber);
        pgn.setPageSize(pageSize);

        return messengerService.getMessages(user.getId(), userTwo, pgn);
    }

    @GetMapping("/messages/{userTwo}")
    private List<MessageDto> getMessagesDefault(@PathVariable int userTwo) {

        return getMessages(userTwo, 1, 10);
    }


    @MessageMapping("/wsMessenger")
    public void greeting(UserMessage message, Principal principal) throws Exception {


        System.out.println("getToUserId->" + message.getToUserId());
        UserEntity currentUser = userService.findByEmail(principal.getName());
        UserEntity toUser = userService.findById(message.getToUserId());

        StandardMessageDto messageDto = new StandardMessageDto();

        messageDto.setText(message.getText());
        messengerService.sendMessageToUser(messageDto, currentUser.getId(), message.getToUserId());


        System.out.println("sender->" + principal.getName());
        //this.template.convertAndSend("/queue/messageinterdum@Quisque.org", new Greeting("TEXTXTXTXTTXT"));

        MessagRecived messageResponse= new MessagRecived();
        messageResponse.setMine(false);
        messageResponse.setSeen(false);
        messageResponse.setFromUserId(currentUser.getId());
        messageResponse.setText(message.getText());
        this.template.convertAndSendToUser(toUser.getEmail(), "/queue/message", messageResponse);

    }

}
