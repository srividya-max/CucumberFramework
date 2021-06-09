package salesforce.stepdefinitions;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src\\test\\java\\salesforce\\features" 
,monochrome = true, glue = "salesforce.stepdefinitions"
, plugin = {"pretty","html:src\\test\\java\\Reports\\Samplereport.html",
		"json:src\\test\\java\\Reports\\Samplereport.json",
		"rerun:src\\test\\java\\Reports\\rerun.txt"})
public class SuitRunner {

}
