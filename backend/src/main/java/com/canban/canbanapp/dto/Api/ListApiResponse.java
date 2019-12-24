package com.canban.canbanapp.dto.Api;

import java.util.List;

public class ListApiResponse<T> {
    private int code;
    private String message;
    private List<T> results;

    public ListApiResponse(int code, String message, List<T> results) {
        this.code = code;
        this.message = message;
        this.results = results;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }
}
