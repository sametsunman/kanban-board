package com.canban.canbanapp.controller;

import com.canban.canbanapp.controller.Abstract.AbstractController;
import com.canban.canbanapp.dto.Api.ApiResponse;
import com.canban.canbanapp.dto.Api.ListApiResponse;
import com.canban.canbanapp.dto.Api.WriteApiResponse;
import com.canban.canbanapp.dto.TaskDTO;
import com.canban.canbanapp.exception.ServiceException;
import com.canban.canbanapp.service.read.TaskServiceRead;
import com.canban.canbanapp.service.write.TaskServiceWrite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@CrossOrigin(
        origins = "*",
        maxAge = 3600
)
@RestController
@RequestMapping("/api/v1")
public class TaskController extends AbstractController<TaskDTO> {

    @Autowired
    TaskServiceRead taskServiceRead;

    @Autowired
    TaskServiceWrite taskServiceWrite;

    @GetMapping("/tasks")
    public ListApiResponse<TaskDTO> findAll() throws ServiceException {
        List<TaskDTO> data;
        try{
            data = taskServiceRead.findAll();
        }catch (ServiceException | ParseException ex){
            return new ListApiResponse<>(404, "Exception", null);
        }
        return new ListApiResponse<>(200,"All Tasks",data);
    }

    @GetMapping("/tasks/{id}")
    public ApiResponse<TaskDTO> findById(@PathVariable int id){
        TaskDTO data;
        try{
            data = taskServiceRead.findById(id);
        } catch (ServiceException | ParseException e) {
            return new ApiResponse<>(404, "Exception", null);
        }
        return new ApiResponse<>(200, "Task", data);
    }

    @GetMapping("/tasks/process")
    public ListApiResponse<TaskDTO> findByProcessName(@RequestParam String name){
        List<TaskDTO> data;
        try{
            data = taskServiceRead.findByProcessName(name);
        } catch (ServiceException | ParseException e) {
            return new ListApiResponse<>(404, "Exception", null);
        }
        return new ListApiResponse<>(200, "Data from Process Name", data);
    }

    @PostMapping("/tasks")
    public WriteApiResponse save(@RequestBody TaskDTO dto){
        try{
            taskServiceWrite.save(dto);
        } catch (ServiceException | ParseException e) {
            return new WriteApiResponse("Error");
        }
        return new WriteApiResponse("Successful");
    }

    @PutMapping("/tasks/{id}")
    public WriteApiResponse update(@RequestBody TaskDTO dto, @PathVariable int id){
        try{
            taskServiceWrite.update(dto);
        }catch (Exception e){
            return new WriteApiResponse("Error");
        }
        return new WriteApiResponse("Successful");
    }

    @DeleteMapping("/tasks/{id}")
    public WriteApiResponse delete(@PathVariable int id){
        try {
            taskServiceWrite.delete(id);
        }catch (ServiceException | ParseException e){
            return new WriteApiResponse("Error");
        }
        return new WriteApiResponse("Successful");
    }
}
