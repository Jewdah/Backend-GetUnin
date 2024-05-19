package com.getunin.service.interfaces;

import com.getunin.dto.CoordinatorRequest;
import com.getunin.entity.Coordinator;

import java.util.List;

public interface CoordinatorService {
    Coordinator createCoordinator(CoordinatorRequest request);
    Coordinator updateCoordinator(CoordinatorRequest request,Long id);
    Coordinator getCoordinatorById(Long id);

    Coordinator getCoordinatorByEmail(String email);
    List<Coordinator> getAllCoordinator();
}
