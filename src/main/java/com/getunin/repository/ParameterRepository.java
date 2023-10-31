package com.getunin.repository;

import com.getunin.entity.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParameterRepository extends JpaRepository<Parameter, Long> {

    List<Parameter> findByParameterRelationShip_Id(Long parameterRelationShip);

    Parameter findParameterByParameterName(String name);

}
