package com.bashko.itmo.repositories;

import com.bashko.itmo.entities.Role;
import com.bashko.itmo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByRolesNotContaining(Role role);

    User findOneByUsername(String userName);

    boolean existsUserByEmail(String email);

    boolean existsUserByUsername(String username);

    User findUserByEmail(String email);

}
