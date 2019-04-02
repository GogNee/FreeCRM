package com.qa.ExtentReportListener;

//import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
//import com.relevantcodes.extentreports.IReporter;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentReportNG implements IReporter {
	private ExtentReports extent;
	//@Override
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites,
			String outputDirectory) {
		//outputDirectory= System.getProperty("user.dir") +"/test-output/Extent.html";
		//extent = new ExtentReports(outputDirectory);
		extent = new ExtentReports("C:\\Eclipse-JEE\\FreeCRMTest\\test-output\\old\\Extent.html", true);
	//	outputDirectory= "C:\\Eclipse-JEE\\FreeCRMTest\\test-output\\";
		
		
	//	extent = new ExtentReports( outputDirectory + File.separator+ "Extent.html", true);
		
		//Iterating over each suite included in the test
		for (ISuite suite : suites) {
			
			 //Getting the results for the said suite
			Map<String, ISuiteResult> result = suite.getResults();

			for (ISuiteResult r : result.values()) {
				ITestContext context = r.getTestContext();

				buildTestNodes(context.getPassedTests(), LogStatus.PASS);
				buildTestNodes(context.getFailedTests(), LogStatus.FAIL);
				buildTestNodes(context.getSkippedTests(), LogStatus.SKIP);
			}
		}

		extent.flush();
		extent.close();
	}

	private void buildTestNodes(IResultMap tests, LogStatus status) {
		ExtentTest test;

		if (tests.size() > 0) {
			for (ITestResult result : tests.getAllResults()) {
				test = extent.startTest(result.getMethod().getMethodName());

				test.setStartedTime(getTime(result.getStartMillis()));
				test.setEndedTime(getTime(result.getEndMillis()));

				for (String group : result.getMethod().getGroups())
					test.assignCategory(group);

				if (result.getThrowable() != null) {
					test.log(status, result.getThrowable());
				} else {
					test.log(status, "Test " + status.toString().toLowerCase()
							+ "ed");
				}

				extent.endTest(test);
			}
		}
	}

	private Date getTime(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}

//	public void start(Reporter report) {
//		// TODO Auto-generated method stub
//		System.out.println(report + "Extent Report by Neeti");
//	}
//
//	public void stop() {
//		// TODO Auto-generated method stub
//		
//	}
//
//	public void flush() {
//		// TODO Auto-generated method stub
//		
//	}
//
//	public void addTest(Test test) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	public void setTestRunnerLogs() {
//		// TODO Auto-generated method stub
//		
//	}

	
}