package com.bashko.itmo.services.interfaceService;

import com.bashko.itmo.entities.Role;
import com.bashko.itmo.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<User> findAllByRolesNotContaining(Role role);

    User findById(Long id);
    List<User> findAll();
    boolean save(User user);
    void deleteById(Long id);
    boolean existsUserByEmail(String email);
    boolean existsUserByUsername(String username);

    User findUserLastAdded();
}
