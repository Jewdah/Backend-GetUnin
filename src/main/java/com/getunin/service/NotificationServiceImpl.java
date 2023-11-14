package com.getunin.service;

import com.getunin.dto.NotificationRequest;
import com.getunin.entity.Notifications;
import com.getunin.entity.PiaTeam;
import com.getunin.entity.Student;
import com.getunin.repository.NotificationRepository;
import com.getunin.service.interfaces.NotificationService;
import com.getunin.service.interfaces.PiaTeamService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    private final PiaTeamService piaTeamService;

    public NotificationServiceImpl(NotificationRepository notificationRepository, PiaTeamService piaTeamService) {
        this.notificationRepository = notificationRepository;
        this.piaTeamService = piaTeamService;
    }

    @Override
    public Notifications sendNotificationOfProject( PiaTeam team, Student studentRequest) {

        Notifications notification = new Notifications();

        notification.setTitle(team.getNameProyect());
        notification.setSubject("Se te ha invitado al proyecto");
        notification.setStatus(true);
        notification.setUserTo(studentRequest.getUser());
        notification.setUserRecovery(team.getStudentOne().getUser());
        notification.setUpdateDate(Timestamp.valueOf(LocalDateTime.now()));

        return notificationRepository.save(notification);
    }

    @Override
    public Notifications acceptNotificationOfProyect(PiaTeam team, Student studentRequest,Long id) {
        Optional<Notifications> notification = notificationRepository.findById(id);

        if (notification.isPresent()){
            PiaTeam teamFind = piaTeamService.getPiaTeamByName(notification.get().getTitle());

            piaTeamService.acceptInvitation(teamFind,studentRequest);
        }

        return null;
    }
}
