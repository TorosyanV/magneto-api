package com.magneto.data.repository;

import com.magneto.data.dataobject.ConnectionStatus;
import com.magneto.data.entity.UserEntity;

import java.util.List;

public interface UserConnectionRepository {

    void setNewConnectionRequest(int userOne, int userTwo);

    void acceptConnectionRequest(int userOne, int userTwo);

    void rejectConnectionRequest(int userOne, int userTwo);

    ConnectionStatus getUserConnectionStatus(int userOne, int userTwo);

    List<UserEntity> getConnectedUsers(int userId);

    List<UserEntity> getRecivedRequests(int userId);

    List<UserEntity> getSentRequests(int userId);
}