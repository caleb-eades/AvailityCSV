package com.eades.availity.model;

import com.eades.availity.Log;
import com.eades.availity.util.EnrolleeComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class CompanyList {
    private Log log;
    private Map<String, List<Enrollee>> companies;

    public CompanyList() {
        this.log = Log.getInstance();
        this.companies = new HashMap<>();
    }

    public void addEnrollee(Enrollee enrollee) {
        if (!companies.keySet().contains(enrollee.getCompany())) {
            log.info("Adding new Company " + enrollee.getCompany());
            List<Enrollee> enrolleeList = new ArrayList<>();
            enrolleeList.add(enrollee);
            companies.put(enrollee.getCompany(), enrolleeList);
            return;
        }

        List<Enrollee> enrollees = companies.get(enrollee.getCompany());
        // Override .equals() on Enrollee to make finding duplicates significantly
        // faster compared to interation. Given a much larger data set, 
        // implementing a binary tree would be a viable option as well,
        // since ID's are unique and compareable
        int existingIndex = enrollees.lastIndexOf(enrollee);
        if (existingIndex >= 0) {
            Enrollee existing = enrollees.get(existingIndex);
            // If the new version number is higher, replace. Otherwise, ignore
            log.info("Replacing record for newer version");
            if (existing.getVersion() < enrollee.getVersion()) {
                enrollees.set(existingIndex, enrollee);
            }
        } else {
            enrollees.add(enrollee);
        }

    }

    public Map<String, List<Enrollee>> getCompanies() {
        return this.companies;
    }

    public void sort() {
        for (Map.Entry<String, List<Enrollee>> entry : companies.entrySet()) {
            log.info("Sorting records for " + entry.getKey() + ", " + entry.getValue().size() + " records");
            Collections.sort(entry.getValue(), new EnrolleeComparator());
        }
    }
}