package com.eades.availity.util;

import com.eades.availity.model.Enrollee;

import java.util.Comparator;

public class EnrolleeComparator implements Comparator<Enrollee> {
    public int compare(Enrollee e1, Enrollee e2) {
        String lastName1 = e1.getLastName();
        String lastName2 = e2.getLastName();

        int result = lastName1.compareToIgnoreCase(lastName2);
        if (result == 0) {
            String firstName1 = e1.getFirstName();
            String firstName2 = e2.getFirstName();
            result = firstName1.compareToIgnoreCase(firstName2);
        }
        return result;
    }
}