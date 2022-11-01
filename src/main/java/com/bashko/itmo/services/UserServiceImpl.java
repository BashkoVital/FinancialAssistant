package com.bashko.itmo.services;

import com.bashko.itmo.entities.Role;
import com.bashko.itmo.entities.User;
import com.bashko.itmo.repositories.RoleRepository;
import com.bashko.itmo.repositories.UserRepository;
import com.bashko.itmo.services.interfaceService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findById(Long id) {
        Optional<User> userFromDb = userRepository.findById(id);
        return userFromDb.orElse(new User());
    }

    @Override
    public boolean existsUserByEmail(String email) {
        return userRepository.existsUserByEmail(email);
    }


    @Override
    public boolean save(User user) {
        if (userRepository.findAll().isEmpty()) {
            roleRepository.save(new Role( "ROLE_ADMIN"));
            roleRepository.save(new Role( "ROLE_USER"));

            user.setRoles(Set.of(roleRepository.findByName("ROLE_ADMIN"), roleRepository.findByName("ROLE_USER")));
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return true;
        } else {
            User userFromDB = userRepository.findOneByUsername(user.getUsername());
            if (userFromDB != null) {
                return false;
            }
            user.setRoles(Collections.singleton(roleRepository.findByName("ROLE_USER")));
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return true;
        }
    }


    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> findAllByRolesNotContaining(Role role) {
        return userRepository.findAllByRolesNotContaining(role);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();/*.stream()
                .map(User::new)
                .collect(Collectors.toList());*/
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findOneByUsername(userName);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    @Override
    public User findUserLastAdded() {

        List<User> userLinkedList = new LinkedList<>();
        userLinkedList = userRepository.findAll();

        return userLinkedList.get(userLinkedList.size() - 1);
    }
}
