package com.example.demo.config;

import com.example.demo.models.Mechanic_Details;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        Mechanic_Details mechanicDetails= userRepository.findByName(username);

        if (mechanicDetails == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new UserInfoUserDetails(mechanicDetails);

    }
}
