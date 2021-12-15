package it.backend.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.xml.crypto.Data;

public class ValidationDTO {

    private BusinessUserDTO user;
    private Data data;

    public BusinessUserDTO getUser() {
        return user;
    }

    public void setUser(BusinessUserDTO user) {
        this.user = user;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
