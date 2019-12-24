package com.canban.canbanapp.controller.Abstract;

import com.canban.canbanapp.dto.Api.ApiResponse;
import com.canban.canbanapp.dto.Api.ListApiResponse;
import com.canban.canbanapp.dto.Api.WriteApiResponse;
import com.canban.canbanapp.exception.ServiceException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public abstract class AbstractController<T> {

    public abstract <S extends T> ListApiResponse<List<T>> findAll() throws ServiceException;

    public abstract <S extends T> ApiResponse<T> findById(@PathVariable int id);

    public abstract <S extends T> WriteApiResponse save(@RequestBody T dto);

    public abstract <S extends T> WriteApiResponse update(@RequestBody T dto, @PathVariable int id);

    public abstract <S extends T> WriteApiResponse delete(@PathVariable int id);

}
