package com.example.springex.error;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ValidationError {

    private List<String> errors;
    private String uri;
    @JsonFormat(shape = JsonFormat.Shape.STRING ,pattern = "dd-MM-yyyy hh:mm:ss")
    private Date timestamp;

    public ValidationError() {
        this.errors = new ArrayList<>();
        this.timestamp = new Date();
    }

    public void addErrors(String error){
        this.errors.add(error);
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public List<String> getErrors() {
        return errors;
    }

    public String getUri() {
        return uri;
    }

    public Date getTimestamp() {
        return timestamp;
    }
}
