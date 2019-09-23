package com.eades.availity.model;

import com.eades.availity.Log;
import com.eades.availity.exception.InvalidDataException;

import java.lang.Integer;
import java.lang.NumberFormatException;

public class Enrollee {
    private String userId;
    private String firstName;
    private String lastName;
    private int version;
    private String company;

    public Enrollee(String[] data) throws InvalidDataException {
        this.userId = data[0];
        this.firstName = data[1];
        this.lastName = data[2];
        this.company = data[4];
        try {
            this.version = Integer.parseInt(data[3]);
        } catch (NumberFormatException e) {
            Log.getInstance().error("Version is not an integer");
        }
    }

    public String getUserId() {
        return this.userId;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public int getVersion() {
        return this.version;
    }

    public String getCompany() {
        return this.company;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Enrollee) {
            Enrollee compare = (Enrollee) o;
            if (this.getUserId().equals(compare.getUserId())) {
                return true;
            }
        }
        return false;
    }
}