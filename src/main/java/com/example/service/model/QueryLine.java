package com.example.service.model;

import java.util.Objects;

public class QueryLine {
    private String service;
    private String question;
    private String responseType;
    private String data;

    public QueryLine(String service, String question, String responseType, String data) {
        this.service = service;
        this.question = question;
        this.responseType = responseType;
        this.data = data;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QueryLine queryLine = (QueryLine) o;
        return service.equals(queryLine.service) &&
                question.equals(queryLine.question) &&
                responseType.equals(queryLine.responseType) &&
                data.equals(queryLine.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(service, question, responseType, data);
    }
}
