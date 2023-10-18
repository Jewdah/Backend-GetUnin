package com.getunin.repository;
import com.getunin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByIdentification(String identification);

    Optional<List<User>> findByRoleId_Id(Long id);
}