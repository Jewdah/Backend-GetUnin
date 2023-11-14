package com.getunin.repository;

import com.getunin.entity.PiaTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PiaTeamRepository extends JpaRepository<PiaTeam,Long> {

    PiaTeam findPiaTeamsByNameProyect(String name);

}
