package info.bowkett.abc;

/**
 * Created by jbowkett on 28/08/2014.
 */
import cucumber.api.junit.Cucumber;
import cucumber.api.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/features"},
                 strict=true,
                 format = {"pretty"},
                 tags = {"~@wip"}
)
public class RunScenariosTest {

}