package com.example.notifymoney.service;

import com.example.notifymoney.repository.UserRepository;
import com.example.notifymoney.repository.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user;
    }

    public void deleteUserById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            userRepository.delete(user);
            System.out.println("пользователь с id " + id + "успешно удален");
        }
    }

    public void createUser(User newUser) {
        userRepository.save(newUser);
        System.out.println("пользователь " + newUser + " добавлен в таблицу");
    }

//    public void updateUser(Long id, User updateUser) throws Exception {
//        userRepository.findById(id).map(
//                existingUser -> {
//                    existingUser.setName(updateUser.getName() == null ? existingUser.getName() : updateUser.getName());
//                    existingUser.setSurname(updateUser.getSurname() == null ? existingUser.getSurname() : updateUser.getSurname());
//                    existingUser.setPhone(updateUser.getPhone() == null ? existingUser.getPhone() : updateUser.getPhone());
//                    existingUser.setBirthday(updateUser.getBirthday() == null ? existingUser.getBirthday() : updateUser.getBirthday());
//                    existingUser.setEmail(updateUser.getEmail() == null ? existingUser.getEmail() : updateUser.getEmail());
//                    existingUser.setTelegram(updateUser.getTelegram() == null ? existingUser.getTelegram() : updateUser.getTelegram());
//                    existingUser.setTelegrambot(updateUser.getTelegrambot() == null ? existingUser.getTelegrambot() : updateUser.getTelegrambot());
//                    return userRepository.save(existingUser);
//                }).orElseThrow(() -> new Exception("пользователь с id " + id + " не найден"));
//    }
}
