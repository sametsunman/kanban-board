package com.canban.canbanapp.service.read;

import com.canban.canbanapp.dto.TaskDTO;
import com.canban.canbanapp.exception.ServiceException;

import java.text.ParseException;
import java.util.List;

public interface TaskServiceRead {
    List<TaskDTO> findAll() throws ServiceException, ParseException;

    TaskDTO findById(int id) throws ServiceException, ParseException;

    List<TaskDTO> findByProcessName(String process) throws ServiceException, ParseException;
}
