package contract;

import java.util.stream.Collectors;

import com.jayway.restassured.config.HeaderConfig;
import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import com.jayway.restassured.module.mockmvc.specification.MockMvcRequestSpecBuilder;
import com.jayway.restassured.module.mockmvc.specification.MockMvcRequestSpecification;
import data.Application;
import data.domain.nodes.User;
import data.repositories.UserRepository;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author Marcin Grzejszczak
 */
@RunWith(SpringJUnit4ClassRunner.class)
// TODO: Consider mocking out the DB part only (that can be tricky since we're using Spring Data REST)
@SpringApplicationConfiguration(Application.class)
@WebIntegrationTest
public class ContractBase {
	@Autowired WebApplicationContext context;
	@Autowired UserRepository repo;

	@Before
	public void setup() {
		RestAssuredMockMvc.webAppContextSetup(context);
		repo.deleteAll();
		repo.save(user());
	}

	User user() {
		User u = new User();
		u.setFirstName("foo");
		u.setLastName("bar");
		u.setEmail("e@mail.com");
		u.setPhone("123-123-123");
		u.setBirthDate(123123L);
		return u;
	}
}
