package com.spring.archivageapplication.Repository;


import com.spring.archivageapplication.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    User findByEmailIgnoreCase(String email);
    public User findByEmail(String email);

    @Query("select u.active from User u where u.email=?1")
    public int getActive(String email);

    @Query("select u.password from User u where u.email=?1")
    public String getPasswordByEmail(String email);
}
