package com.eades.availity;

import com.eades.availity.model.CompanyList;
import com.eades.availity.model.Enrollee;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class EnrolleeFileWriter {

    private Log log;
    private String outputDir;
    
    EnrolleeFileWriter(String outputDir) {
        this.outputDir = outputDir;
        this.log = Log.getInstance();
    }

    public void writeFiles(CompanyList companyList) {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        for (Map.Entry<String, List<Enrollee>> entry : companyList.getCompanies().entrySet()) {
            log.info("Writing records for " + entry.getKey() + ", " + entry.getValue().size() + " records");
            String outputFile = outputDir + "\\" + entry.getKey() + "_" + timeStamp + ".csv";
            log.info("Writing to " + outputFile);

            Charset utf8 = StandardCharsets.UTF_8;
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile, true), utf8)
            )) {
                for (int i = 0; i < entry.getValue().size(); i++) {
                    Enrollee enrollee = entry.getValue().get(i);
                    String line = enrollee.getUserId() + 
                    "," + enrollee.getFirstName() + 
                    "," + enrollee.getLastName() + 
                    "," + enrollee.getVersion() + 
                    "," + enrollee.getCompany();
                    log.debug("Writing entry " + line);
                    writer.write(line + "\n");
                }
            } catch (IOException e) {
                log.error("Error writing to file for " + entry.getKey());
            }
        }
    }

}