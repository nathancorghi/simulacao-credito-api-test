package br.com.simulacao.credito.test;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources/features"},
                    plugin = {"pretty", "json:target/cucumber/cucumber.json"},
                    tags = {""})
public class RunCucumber {
}