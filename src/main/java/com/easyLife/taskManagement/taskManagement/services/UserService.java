package com.easyLife.taskManagement.taskManagement.services;

import com.easyLife.taskManagement.taskManagement.dto.UserDtoLogin;
import com.easyLife.taskManagement.taskManagement.entities.User;
import com.easyLife.taskManagement.taskManagement.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(
                ()-> new RuntimeException("User not found with email: "+username)
        );
    }

    public User getUserById(Long userId){
        return userRepository.findById(userId).orElseThrow(
                ()-> new RuntimeException("User not found")
        );
    }

    public UserDtoLogin signUpUser(UserDtoLogin userDtoLogin){
        Optional<User> olderUser = userRepository.findByEmail(userDtoLogin.getEmail());
        if(olderUser.isPresent()){
            throw new RuntimeException("User already exist by email: "+userDtoLogin.getEmail());
        }
        User user = modelMapper.map(userDtoLogin,User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User user1 = userRepository.save(user);
        return modelMapper.map(user1, UserDtoLogin.class);
    }
}
