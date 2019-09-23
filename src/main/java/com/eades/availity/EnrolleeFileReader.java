package com.eades.availity;

import com.eades.availity.exception.InvalidDataException;
import com.eades.availity.model.Enrollee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.Exception;
import com.eades.availity.model.CompanyList;

public class EnrolleeFileReader {

    private Log log;
    private static String inputFile;

    EnrolleeFileReader(String inputFile) {
        this.inputFile = inputFile;
        this.log = Log.getInstance();
    }

    public CompanyList readFile() {
        log.info("Reading csv file " + inputFile);
        CompanyList companyList = new CompanyList();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            String line = "";
            while ((line = reader.readLine()) != null) {
                log.debug(line);
                String[] data = line.split(",");
                if (data.length != 5) {
                    log.error("Invalid record: " + line);
                } else {
                    Enrollee enrollee = new Enrollee(data);
                    companyList.addEnrollee(enrollee);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.warn(e.getMessage());
        }

        return companyList;
    }
}