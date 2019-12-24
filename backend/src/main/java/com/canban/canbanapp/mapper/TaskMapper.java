package com.canban.canbanapp.mapper;

import com.canban.canbanapp.dto.TaskDTO;
import com.canban.canbanapp.model.Task;
import org.modelmapper.PropertyMap;

public class TaskMapper extends PropertyMap<Task, TaskDTO> {

    @Override
    protected void configure() {
        this.map().setId(this.source.getId());
        this.map().setName(this.source.getName());
        this.map().setDescription(this.source.getDescription());
        this.map().setProcess(this.source.getProcess());
        this.map().setDueDate(this.source.getDueDate());
        this.map().setCompletedDate(this.source.getCompletedDate());
    }
}
