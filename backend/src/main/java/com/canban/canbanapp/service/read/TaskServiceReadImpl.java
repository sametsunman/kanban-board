package com.canban.canbanapp.service.read;

import com.canban.canbanapp.dto.TaskDTO;
import com.canban.canbanapp.exception.ServiceException;
import com.canban.canbanapp.model.Task;
import com.canban.canbanapp.model.TaskLogging;
import com.canban.canbanapp.repository.TaskLoggingRepository;
import com.canban.canbanapp.repository.TaskRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceReadImpl implements TaskServiceRead {

    TaskLogging log = new TaskLogging();

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private TaskLoggingRepository loggingRepository;

    private Date setDate() throws ParseException {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(formatter.format(date));

        return date1;
    }

    @Override
    public List<TaskDTO> findAll() throws ServiceException, ParseException {
        List<Task> data;
        try{
            data = taskRepository.findAll();
        }catch (Exception ex){
            throw new ServiceException("404", "Data Not Found");
        }
        log.setDescription("Task Find All ");
        log.setDateTime(setDate());
        loggingRepository.save(log);

        return mapper.map(data, new TypeToken<List<TaskDTO>>(){
        }.getType());
    }

    @Override
    public TaskDTO findById(int id) throws ServiceException, ParseException{
        Optional<Task> data;
        try{
            data = taskRepository.findById(id);
        }catch (Exception ex){
            throw new ServiceException("404", "One Data Not Found");
        }
        log.setDescription("Task FindById ");
        log.setDateTime(setDate());
        loggingRepository.save(log);
        return mapper.map(data.get(), new TypeToken<TaskDTO>(){
        }.getType());
    }

    @Override
    public List<TaskDTO> findByProcessName(String process) throws ServiceException,ParseException{
        List<Task> data;
        try{
            data = taskRepository.findByProcess(process);
        }catch (Exception ex){
            throw new ServiceException("404", "One Data Not Found");
        }
        log.setDescription("Task FindByProcessName ");
        log.setDateTime(setDate());
        loggingRepository.save(log);
        return mapper.map(data, new TypeToken<List<TaskDTO>>(){
        }.getType());
    }
}
