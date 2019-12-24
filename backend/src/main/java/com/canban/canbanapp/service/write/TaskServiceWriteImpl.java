package com.canban.canbanapp.service.write;

import com.canban.canbanapp.dto.TaskDTO;
import com.canban.canbanapp.exception.ServiceException;
import com.canban.canbanapp.model.Task;
import com.canban.canbanapp.model.TaskLogging;
import com.canban.canbanapp.repository.TaskLoggingRepository;
import com.canban.canbanapp.repository.TaskRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Service
public class TaskServiceWriteImpl implements TaskServiceWrite {

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
    public void save(TaskDTO taskDTO) throws ServiceException, ParseException {
        Task data = new Task();
        data.setId(taskDTO.getId());
        data.setName(taskDTO.getName());
        data.setDescription(taskDTO.getDescription());
        data.setProcess("TODO");
        data.setDueDate(taskDTO.getDueDate());
        data.setCompletedDate(null);

        try{
            taskRepository.save(data);
        }catch (Exception ex){
            throw new ServiceException("404", "Cannot add");
        }

        log.setDescription("Task Create ");
        log.setDateTime(setDate());
        loggingRepository.save(log);
    }

    @Override
    public void update(TaskDTO taskDTO) throws ServiceException, ParseException {
        Task data = new Task();
        data.setId(taskDTO.getId());
        data.setName(taskDTO.getName());
        data.setDescription(taskDTO.getDescription());
        data.setProcess(taskDTO.getProcess());
        data.setDueDate(taskDTO.getDueDate());
        if(taskDTO.getProcess().equals("DONE")){
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(formatter.format(date));
            data.setCompletedDate(date1);
        }
        try{
            taskRepository.save(data);
        }catch (Exception ex){
            throw new ServiceException("404", "Cannot update");
        }
        log.setDescription("Task Update ");
        log.setDateTime(setDate());
        loggingRepository.save(log);
    }

    @Override
    public void delete(int id) throws ServiceException, ParseException {
        try{
            taskRepository.deleteById(id);
        }catch (Exception ex){
            throw new ServiceException("404", "Cannot delete");
        }

        log.setDescription("Task Delete ");
        log.setDateTime(setDate());
        loggingRepository.save(log);
    }

}
