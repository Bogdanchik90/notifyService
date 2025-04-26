package com.example.notifymoney.service;

import com.example.notifymoney.exceptions.EntityNotFoundException;
import com.example.notifymoney.repository.UserRepository;
import com.example.notifymoney.repository.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public void deleteUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь с id " + id + " не найден"));
        userRepository.delete(user);
    }

    @Transactional
    public void createUser(User newUser) {
        userRepository.save(newUser);
        System.out.println("пользователь " + newUser + " добавлен в таблицу");
    }

    @Transactional
    public User updateUser(Long id, User updateUser) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь с id " + id + " не найден"));

        Optional.ofNullable(updateUser.getName()).ifPresent(existingUser::setName);
        Optional.ofNullable(updateUser.getSurname()).ifPresent(existingUser::setSurname);
        Optional.ofNullable(updateUser.getPhone()).ifPresent(existingUser::setPhone);
        Optional.ofNullable(updateUser.getBirthday()).ifPresent(existingUser::setBirthday);
        Optional.ofNullable(updateUser.getEmail()).ifPresent(existingUser::setEmail);
        Optional.ofNullable(updateUser.getTelegram()).ifPresent(existingUser::setTelegram);
        Optional.ofNullable(updateUser.getTelegrambot()).ifPresent(existingUser::setTelegrambot);
        return userRepository.save(existingUser);
    }
}
