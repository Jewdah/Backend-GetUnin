package com.getunin.repository;

import com.getunin.dto.CoordinatorRequest;
import com.getunin.entity.Coordinator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoordinatorRepository extends JpaRepository<Coordinator,Long> {

    Coordinator findByUser_Email(String email);
}
