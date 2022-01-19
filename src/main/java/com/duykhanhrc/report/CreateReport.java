package com.duykhanhrc.report;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

//https://stackoverflow.com/questions/53780902/report-builder-says-cucumber-is-not-a-valid-report
public class CreateReport {

    private void generateReportForJsonFiles(File reportOutputDirectory, List<String> jsonFiles) {
        String buildNumber = "1";
        String projectName = "StackOverflow example";

        Configuration configuration = new Configuration(reportOutputDirectory, projectName);
        configuration.setParallelTesting(false);
//        configuration.setJenkinsBasePath(jenkinsBasePath);
        configuration.setRunWithJenkins(false);
        configuration.setBuildNumber(buildNumber);

        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
        reportBuilder.generateReports();
    }

    public static void main(String[] args) {
        new CreateReport().generateReportForJsonFiles(new File("target/"),
                Arrays.asList("target/cucumber-reports/Cucumber.json"));
    }
}