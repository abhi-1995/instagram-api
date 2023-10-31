package com.insta.instagram.service.impl;

import com.insta.instagram.dto.UserDTO;
import com.insta.instagram.exceptions.UserException;
import com.insta.instagram.model.User;
import com.insta.instagram.repository.UserRepository;
import com.insta.instagram.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User registerUser(User user) throws UserException {
        Optional<User> isEmailExist = userRepository.findByEmail(user.getEmail());

        if (isEmailExist.isPresent()) {
            throw new UserException("User already exist with email id "+user.getEmail());
        }

        Optional<User> isUsernameExist = userRepository.findByUsername(user.getUsername());
        if (isUsernameExist.isPresent()) {
            throw new UserException("Username is already taken");
        }

        if (user.getEmail() == null || user.getPassword() == null || user.getUsername() == null) {
            throw new UserException("all fields are required");
        }

        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setUsername(user.getUsername());
        newUser.setName(user.getName());



        return userRepository.save(newUser);
    }

    @Override
    public User findUserById(Integer userId) throws UserException {
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }
        throw new UserException("User not exist with id: "+userId);
    }



    @Override
    public User findUserByProfile(String token) throws UserException {
        return null;
    }

    @Override
    public User findUserByUsername(Integer username) throws UserException {
        return null;
    }

    @Override
    public String followUser(Integer reqUserId, Integer followUserId) throws UserException {
        User reqUser = findUserById(reqUserId);
        User followUser = findUserById(followUserId);

        List<UserDTO> userDTOList = this.processFollowerFollowingUser(reqUser,followUser);

        reqUser.getFollowing().add(userDTOList.get(1));
        followUser.getFollower().add(userDTOList.get(0));


        userRepository.save(reqUser);
        userRepository.save(followUser);


        return "You are following "+followUser.getUsername();
    }

    @Override
    public String unFollowUser(Integer reqUserId, Integer followUserId) throws UserException {
        User reqUser = findUserById(reqUserId);
        User followUser = findUserById(followUserId);

        List<UserDTO> userDTOList = this.processFollowerFollowingUser(reqUser,followUser);

        reqUser.getFollowing().remove(userDTOList.get(1));
        followUser.getFollower().remove(userDTOList.get(0));


        userRepository.save(reqUser);
        userRepository.save(followUser);

        return "You have unfollowed "+followUser.getUsername();
    }

    @Override
    public List<User> findUserByIds(List<Integer> userIds) throws UserException {
        return null;
    }

    @Override
    public List<User> searchUser(String query) throws UserException {
        return null;
    }

    @Override
    public User updateUserDetails(User updatedUser, User existingUser) throws UserException {
        return null;
    }

    private List<UserDTO> processFollowerFollowingUser(User reqUser,User followUser) {
        UserDTO follower = new UserDTO();
        follower.setEmail(reqUser.getEmail());
        follower.setId(reqUser.getId());
        follower.setName(reqUser.getName());
        follower.setUserImage(reqUser.getImage());
        follower.setUserName(reqUser.getUsername());

        UserDTO following = new UserDTO();
        following.setEmail(followUser.getEmail());
        following.setId(followUser.getId());
        following.setName(followUser.getName());
        following.setUserImage(followUser.getImage());
        following.setUserName(followUser.getUsername());

        List<UserDTO> result = new ArrayList<>();
        result.add(follower);
        result.add(following);

        return result;
    }
}
