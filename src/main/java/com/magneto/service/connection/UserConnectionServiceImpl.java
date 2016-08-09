package com.magneto.service.connection;

import com.magneto.data.entity.UserEntity;
import com.magneto.dto.UserInfoDto;
import com.magneto.location.Geolocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magneto.data.dataobject.ConnectionStatus;
import com.magneto.data.repository.UserConnectionRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userConnectionService")
@Transactional
public class UserConnectionServiceImpl implements UserConnectionService {

    @Autowired
    private UserConnectionRepository userConnectionRepository;


    @Override
    public void setNewConnectionRequest(int userOne, int userTwo) {

        userConnectionRepository.setNewConnectionRequest(userOne, userTwo);
    }


    @Override
    public void acceptConnectionRequest(int userOne, int userTwo) {

        userConnectionRepository.acceptConnectionRequest(userOne, userTwo);
    }


    @Override
    public void rejectConnectionRequest(int userOne, int userTwo) {

        userConnectionRepository.rejectConnectionRequest(userOne, userTwo);
    }

    public ConnectionStatus getUserConnectionStatus(int userOne, int userTwo) {

        return userConnectionRepository.getUserConnectionStatus(userOne, userTwo);

    }

    @Override
    public List<UserInfoDto> getConnectedUsers(int userId) {
        List<UserEntity> userEntities = userConnectionRepository.getConnectedUsers(userId);
        List<UserInfoDto> userInfos = new ArrayList<>();
        for (UserEntity userEntity : userEntities) {
            UserInfoDto userInfo = new UserInfoDto();
            userInfo.setId(userEntity.getId());
            userInfo.setName(userEntity.getFirstName());
            Geolocation location = new Geolocation();
            location.setLatitude(userEntity.getCurrentLocation().getLatitude());
            location.setLongitude(userEntity.getCurrentLocation().getLongitude());
            userInfo.setLocation(location);
            userInfo.setSex(userEntity.isSex());

            userInfos.add(userInfo);
        }
        return userInfos;
    }


    @Override
    public List<UserInfoDto> getReceivedRequests(int userId) {
        List<UserEntity> userEntities = userConnectionRepository.getRecivedRequests(userId);
        List<UserInfoDto> userInfos = new ArrayList<>();
        for (UserEntity userEntity : userEntities) {
            UserInfoDto userInfo = new UserInfoDto();
            userInfo.setId(userEntity.getId());
            userInfo.setName(userEntity.getFirstName());
            Geolocation location = new Geolocation();
            location.setLatitude(userEntity.getCurrentLocation().getLatitude());
            location.setLongitude(userEntity.getCurrentLocation().getLongitude());
            userInfo.setLocation(location);
            userInfo.setSex(userEntity.isSex());

            userInfos.add(userInfo);
        }
        return userInfos;
    }

    @Override
    public List<UserInfoDto> getSentRequests(int userId) {
        List<UserEntity> userEntities = userConnectionRepository.getSentRequests(userId);
        List<UserInfoDto> userInfos = new ArrayList<>();
        for (UserEntity userEntity : userEntities) {
            UserInfoDto userInfo = new UserInfoDto();
            userInfo.setId(userEntity.getId());
            userInfo.setName(userEntity.getFirstName());
            Geolocation location = new Geolocation();
            location.setLatitude(userEntity.getCurrentLocation().getLatitude());
            location.setLongitude(userEntity.getCurrentLocation().getLongitude());
            userInfo.setLocation(location);
            userInfo.setSex(userEntity.isSex());

            userInfos.add(userInfo);
        }
        return userInfos;
    }
}
