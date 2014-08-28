package info.bowkett.abc;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import sun.security.util.PendingException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


/**
 * Created by jbowkett on 28/08/2014.
 */
public class StepDefinitions {

  @When("^\"(.*?)\" posts \"(.*?)\"$")
  public void posts(String arg1, String arg2) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
  }
}
