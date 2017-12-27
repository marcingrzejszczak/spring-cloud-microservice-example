package application.integration;

import java.util.List;

import application.Application;
import application.clients.UserClient;
import application.models.User;
import org.assertj.core.api.BDDAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Marcin Grzejszczak
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
// TODO: If there was separated HAL resolving configuration then we wouldn't have to start the whole thing
@AutoConfigureStubRunner(ids = "org.kbastani:users-microservice", workOffline = true)
@ActiveProfiles("test")
public class UserClientTests {

	@Autowired UserClient userClient;

	@Test
	public void should_call_user() {
		List<User> users = userClient.findById("0");

		BDDAssertions.then(users.size()).isEqualTo(1);
		User user = users.get(0);
		BDDAssertions.then(user.getFirstName()).isEqualTo("foo");
		BDDAssertions.then(user.getLastName()).isEqualTo("bar");
		BDDAssertions.then(user.getBirthDate()).isNotNull();
		BDDAssertions.then(user.getEmail()).isEqualTo("e@mail.com");
		BDDAssertions.then(user.getPhone()).isEqualTo("123-123-123");
	}
}
