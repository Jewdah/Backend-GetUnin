package com.getunin.service.interfaces;


import com.getunin.dto.NotificationRequest;
import com.getunin.entity.Notifications;
import com.getunin.entity.PiaTeam;
import com.getunin.entity.Student;

public interface NotificationService {

    Notifications sendNotificationOfProject( PiaTeam team, Student studentRequest);

    Notifications acceptNotificationOfProyect(PiaTeam team, Student studentRequest,Long id);


}
