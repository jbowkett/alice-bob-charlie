package info.bowkett.abc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by jbowkett on 29/08/2014.
 */
public class StepDefinitionsTest {

  @Test
  public void testStripPosessive(){
    final String stripped = new StepDefinitions().stripPosessive("Alice's");
    assertEquals("Alice", stripped);
  }

}
