package com.getunin.service.interfaces;

import com.getunin.dto.ExceptionData;
import com.getunin.modelview.ResponseMessage;

public interface ExceptionService {

    ResponseMessage createException(Long id, String nameException, String nameMetod, Object request);
}
