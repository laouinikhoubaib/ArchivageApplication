package com.spring.archivageapplication.Repository;


import com.spring.archivageapplication.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface userRepository extends JpaRepository<User, Long> {

    List<User> findByUsername(String username);

}
