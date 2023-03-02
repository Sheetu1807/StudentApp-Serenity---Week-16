package com.studentapp.cucumber;


import com.studentapp.testbase.TestBase;
import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

/**
 * Created by bhavesh
 */

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/java/resources/feature"
        //tags = {"SMOKE"}
        )
public class CucumberRunner extends TestBase {

}
