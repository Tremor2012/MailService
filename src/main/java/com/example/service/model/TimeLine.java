package com.example.service.model;

import java.util.Objects;

public class TimeLine {
    private String service;
    private String question;
    private String responseType;
    private String data;
    private String time;

    public TimeLine(String service, String question, String responseType, String data, String time) {
        this.service = service;
        this.question = question;
        this.responseType = responseType;
        this.data = data;
        this.time = time;
    }

    public String getService() {
        return service;
    }

    public String getQuestion() {
        return question;
    }

    public String getResponseType() {
        return responseType;
    }

    public String getData() {
        return data;
    }

    public String getTime() {
        return time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeLine timeLine = (TimeLine) o;
        return service.equals(timeLine.service) &&
                question.equals(timeLine.question) &&
                responseType.equals(timeLine.responseType) &&
                data.equals(timeLine.data) &&
                time.equals(timeLine.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(service, question, responseType, data, time);
    }
}
