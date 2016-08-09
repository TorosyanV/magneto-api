package com.magneto.data.dataobject;

import com.magneto.data.entity.UserEntity;

/**
 * Created by Vazgen on 3/16/2016.
 */
public class UserConnectionDetails {
    private UserEntity user;

    public ConnectionStatus getConnectionStatus() {
        return connectionStatus;
    }

    public UserConnectionDetails setConnectionStatus(ConnectionStatus connectionStatus) {
        this.connectionStatus = connectionStatus;
        return this;
    }

    public UserEntity getUser() {
        return user;
    }

    public UserConnectionDetails setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    private ConnectionStatus connectionStatus;

    public UserConnectionDetails(ConnectionStatus connectionStatus, UserEntity user) {
        this.connectionStatus = connectionStatus;
        this.user = user;
    }




}
