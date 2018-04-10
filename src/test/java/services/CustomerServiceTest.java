
package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class CustomerServiceTest extends AbstractTest {

	//Service under test

	@Autowired
	private CustomerService	customerService;


	//Test template

	protected void Template(final String username, final String username2, final Class<?> expected) {
		Class<?> caught = null;

		try {
			this.authenticate(username);

			//Creation

			this.unauthenticate();
			this.authenticate(username2);

			//Listing

			//Edition

			//Deletion

			this.unauthenticate();
		} catch (final Throwable oops) {

			caught = oops.getClass();

		}

		this.checkExceptions(expected, caught);
	}

	//Driver for multiple tests under the same template.

	@Test
	public void Driver() {

		final Object testingData[][] = {

			//Test #01: Correct execution of test. Expected true.
			{

			},

			//Test #02: Expected false.
			{

			},

			//Test #03: Expected false.
			{

			}

		};

		for (int i = 0; i < testingData.length; i++)
			this.Template((String) testingData[i][0], (String) testingData[i][1], (Class<?>) testingData[i][12]);
	}
}
