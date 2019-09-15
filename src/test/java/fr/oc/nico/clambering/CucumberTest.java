package fr.oc.nico.clambering;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "pretty"}, glue = {"fr.oc.nico.clambering.stepdefs"}, features = {"src/test/resources/features"})
public class CucumberTest extends ClamberingApplicationTests {
}
