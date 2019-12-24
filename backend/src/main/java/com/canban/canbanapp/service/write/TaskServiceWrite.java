package com.canban.canbanapp.service.write;

import com.canban.canbanapp.dto.TaskDTO;
import com.canban.canbanapp.exception.ServiceException;

import java.text.ParseException;

public interface TaskServiceWrite {
    void save(TaskDTO taskDTO) throws ServiceException, ParseException;

    void update(TaskDTO taskDTO) throws ServiceException, ParseException;

    void delete(int id) throws ServiceException, ParseException;
}
