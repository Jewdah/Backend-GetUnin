package com.getunin.service.interfaces;

import com.getunin.dto.CoordinatorRequest;
import com.getunin.dto.EducatorRequest;
import com.getunin.entity.Educator;

import java.util.List;

public interface EducatorService {

    Educator createEducator(EducatorRequest request);

    Educator updateEducator(EducatorRequest request,Long id);

    Educator getEducatorById(Long id);

    Educator getEducatorByEmail(String email);

    List<Educator> getAllEducator();

}
