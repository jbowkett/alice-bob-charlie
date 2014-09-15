package info.bowkett.abc.dal;

import info.bowkett.abc.domain.User;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * InMemoryUserRepository Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Sep 15, 2014</pre>
 */
public class InMemoryUserRepositoryTest {

  private InMemoryUserRepository inMemoryUserRepository;

  @Before
  public void before() throws Exception {
    inMemoryUserRepository = new InMemoryUserRepository();
  }


  /**
   * Method: get(String userName)
   */
  @Test
  public void testGetAlwaysReturnsAUserEvenWhenEmpty() throws Exception {
    assertNotNull(inMemoryUserRepository.get("neverSeenUserName"));
  }

  @Test
  public void testAlwaysReturnTheSameUserInstanceForTheSameUsername(){
    final String userName = "testUser";
    final User userFirstTime = inMemoryUserRepository.get(userName);
    final User userSecondTime = inMemoryUserRepository.get(userName);
    assertEquals(userFirstTime, userSecondTime);
  }

}
