package com.example.controlwork.service;

import com.example.controlwork.dto.UserDto;
import com.example.controlwork.dao.UserDao;
import com.example.controlwork.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserDao userDao;
    private final PasswordEncoder encoder;

    public void saveUser(UserDto userDto) {
        log.info("Saved user:"+userDto.getEmail());
        userDao.save(User.builder()
                .userName(userDto.getUserName())
                .email(userDto.getEmail())
                .password(encoder.encode(userDto.getPassword()))
                .build());
    }

    public List<UserDto> getAllUsers() {
        List<User> users = userDao.getAllUsers();
        return users.stream()
                .map(e -> UserDto.builder()
                        .id(e.getId())
                        .userName(e.getUserName())
                        .email(e.getEmail())
                        .password(e.getPassword()).build()
                ).collect(Collectors.toList());
    }

    public String loginUser(UserDto userDto) {
        boolean isExists = userDao.isExists(userDto.getEmail());
        if (isExists) {
            String storedPassword = userDao.getPasswordByEmail(userDto.getEmail());
            if (encoder.matches(userDto.getPassword(), storedPassword)) {
                log.info("Successful LOGIN!");
                return "Successful LOGIN!";
            } else {
                log.error("Incorrect PASSWORD!");
                return "Incorrect PASSWORD!";
            }
        } else {
            log.error("Such user does not exist!");
            return "Such user does not exist!";
        }
    }

    public int getIdByEmail(String email){
        return  userDao.getIdByEmail(email);
    }

    public UserDto getUserById(int userId){
        User user=userDao.getUserById(userId);
        return UserDto.builder()
                .userName(user.getUserName())
                .email(user.getEmail())
                .id(user.getId())
                .build();
    }
}
