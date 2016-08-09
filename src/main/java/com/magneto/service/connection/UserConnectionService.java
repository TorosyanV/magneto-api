package com.magneto.service.connection;

import com.magneto.data.dataobject.ConnectionStatus;
import com.magneto.dto.UserInfoDto;

import java.util.List;

public interface UserConnectionService {
	void setNewConnectionRequest(int userOne, int userTwo);
	void acceptConnectionRequest(int userOne, int userTwo);
	void rejectConnectionRequest(int userOne, int userTwo);
	ConnectionStatus getUserConnectionStatus(int userOne, int userTwo);
	List<UserInfoDto> getConnectedUsers(int userId);
	List<UserInfoDto> getSentRequests(int userId);
	List<UserInfoDto> getReceivedRequests(int userId);
}