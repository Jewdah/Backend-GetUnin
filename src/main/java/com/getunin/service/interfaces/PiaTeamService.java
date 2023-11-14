package com.getunin.service.interfaces;

import com.getunin.dto.PiaTeamRequest;
import com.getunin.entity.PiaTeam;
import com.getunin.entity.Student;
import com.getunin.modelview.ResponseMessage;

import java.util.List;

public interface PiaTeamService {

    PiaTeam createPiaTeam(PiaTeamRequest request);

    PiaTeam updatePiaTeam(PiaTeamRequest request,Long id);
    PiaTeam getPiaTeamById(Long id);
    PiaTeam getPiaTeamByName(String name);
    ResponseMessage deletePiaById(Long id);

    ResponseMessage inviteStudentsToProject(String email, PiaTeam team);
    ResponseMessage acceptInvitation(PiaTeam team, Student studentRequest);

    List<PiaTeam> getAllPiaTeamByObject(PiaTeamRequest request,Long id);

}
