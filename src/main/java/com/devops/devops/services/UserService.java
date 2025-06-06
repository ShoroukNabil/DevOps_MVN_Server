package com.devops.devops.services;

import com.devops.devops.daos.UserDao;
import com.devops.devops.entities.User;
import com.devops.devops.models.BaseResponse;
import com.devops.devops.models.UserListModel;
import com.devops.devops.models.UserModel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {

    private final UserDao userDao;

    public BaseResponse create(UserModel userModel) {
        BaseResponse response = new BaseResponse();
        try{
            userDao.save(createUserEntity(userModel));
            response.setReplyCode("200");
            response.setReplyMessage("CREATED_SUCCESSFULLY");
        } catch (Exception exception){
            response.setReplyCode("422");
            response.setReplyMessage("CREATED_FAILURE");
        }
        return response;
    }

    private User createUserEntity(UserModel userModel) {
        User user = new User();
        constructUserEntity(userModel,user);
        return user;
    }


    private void constructUserEntity(UserModel userModel, User user) {
        user.setName(userModel.getName());
        user.setEmail(userModel.getEmail());
        user.setPassword(userModel.getPassword());
    }

    private UserModel constructUserModel(User user) {
        UserModel userModel = new UserModel();
        userModel.setName(user.getName());
        userModel.setEmail(user.getEmail());
        userModel.setPassword(user.getPassword());
        return userModel;
    }

    public UserListModel getAll() {
        List<User> userList = (List<User>) userDao.findAll();
        return constructUserListModel(userList);
    }

    public BaseResponse login(String email, String password) {
        User user= userDao.findByEmailAndPassword(email, password);
        BaseResponse response = new BaseResponse();
       try {
           if (user != null) {
               response.setReplyCode("200");
               response.setReplyMessage("LOGIN_SUCCESSFULLY");
           } else {
               response.setReplyCode("422");
               response.setReplyMessage("Login Failed");
           }
       }catch (Exception exception){
           log.error("Exception While login {}",exception.getMessage());
       }
       log.debug("Response : {}", response);
        return response;
    }

    private UserListModel constructUserListModel(List<User> userList) {
        UserListModel userListModel = new UserListModel();
        List<UserModel> userModelList = new ArrayList<>();
        userList.forEach(user -> {
            UserModel userModel = constructUserModel(user);
            userModelList.add(userModel);
        });
        userListModel.setUserModelList(userModelList);
        return userListModel;
    }

}
