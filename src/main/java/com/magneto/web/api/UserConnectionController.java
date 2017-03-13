package com.magneto.web.api;

import java.util.List;

import com.magneto.dto.UserInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.magneto.data.dataobject.ConnectionStatus;
import com.magneto.dto.User;
import com.magneto.service.connection.UserConnectionService;

@RestController
@RequestMapping({"/connection", "/api/connection"})
public class UserConnectionController extends AuthorizedController {

    @Autowired
    private UserConnectionService userConnectionService;

    @GetMapping("/status/{userTwo}")
    private ConnectionStatus getUserConnectionStatus(@PathVariable int userTwo) {

        User user = super.getUser();

        return userConnectionService.getUserConnectionStatus(user.getId(), userTwo);

    }

//    @RequestMapping(value = "/all", method = RequestMethod.GET)
//    private List<ConnectionStatus> getUserConnections() {
//
//        User user = super.getUser();
//        List<ConnectionStatus> availableStatuses = Arrays.asList(ConnectionStatus.CONNECTED, ConnectionStatus.PENDING);
//        return userConnectionService.getUserConnections(user.getId(), availableStatuses);
//
//    }

    @GetMapping("/sent")
    private List<UserInfoDto> getUserSentConnections() {
        User user = super.getUser();
        return userConnectionService.getSentRequests(user.getId());

    }

    @GetMapping("/received")
    private List<UserInfoDto> getUserReceivedConnections() {
        User user = super.getUser();
        return userConnectionService.getReceivedRequests(user.getId());

    }

    @GetMapping("/connected")
    private List<UserInfoDto> getUserConnectedConnections() {
        User user = super.getUser();
        return userConnectionService.getConnectedUsers(user.getId());

    }


    ///TODO SET POST

    @PostMapping("/sendrequest")
    private void setNewConnectionRequest(@RequestBody int userId) {
        User user = super.getUser();
        userConnectionService.setNewConnectionRequest(user.getId(), userId);

    }

    @PostMapping("/acceptrequest")
    private void acceptConnectionRequest(@RequestBody  int userId) {
        User user = super.getUser();
        userConnectionService.acceptConnectionRequest(user.getId(), userId);

    }

    @PostMapping("/rejectrequest")
    private void rejectConnectionRequest(@RequestBody  int userId) {
        User user = super.getUser();
        userConnectionService.rejectConnectionRequest(user.getId(), userId);

    }

}
