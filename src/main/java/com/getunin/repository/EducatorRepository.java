package com.getunin.repository;

import com.getunin.entity.Educator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducatorRepository extends JpaRepository<Educator,Long> {

    Educator findByUser_Email(String email);
}
