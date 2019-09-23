package com.eades.availity;

import com.eades.availity.model.CompanyList;

import java.util.Collections;
import java.lang.String;
import java.util.Map;

/**
 * Will read .\samples\sample1.csv and output to .\out folder, logging to .\logs by default
 * These can be changed with -csv <input file>, -outDir <output folder> and -logDir <log folder>
 */
public class App {
    public static void main(String[] args) {
        Log log = Log.getInstance();
        log.setLogLevel(Log.LogLevel.INFO);

        String inputFile = System.getProperty("user.dir") + "\\samples\\sample1.csv";
        String logDir = System.getProperty("user.dir") + "\\logs";
        String outDir = System.getProperty("user.dir") + "\\out";

        for (int i = 0; i < args.length; i++) {
            switch(args[i]) {
                case "-csv":
                    i++;
                    inputFile = args[i];
                    break;
                case "-logDir":
                    i++;
                    logDir = args[i];
                    break;
                case "-outDir":
                    i++;
                    outDir = args[i];
                    break;
                default:
                    break;
            }
        }

        log.info("*******************************");
        log.info("inputFile: " + inputFile);
        log.info("logDir: " + logDir);
        log.info("outDir: " + outDir);
        log.info("*******************************");

        // Read the file to a map
        EnrolleeFileReader reader = new EnrolleeFileReader(inputFile);
        CompanyList companyList = reader.readFile();
        companyList.sort();

        // Write the records to the new csv files
        EnrolleeFileWriter writer = new EnrolleeFileWriter(outDir);
        writer.writeFiles(companyList);
    }
}
