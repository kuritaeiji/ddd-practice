package dddpractice.factory.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dddpractice.domain.user.User;
import dddpractice.domain.user.UserRepository;
import dddpractice.domain.user.UserType;

@Component
public class TestUserDataCreator {

	@Autowired
	UserRepository userRepository;

	public User create(Long id, String email, String name, UserType userType, Long version) {
		User user = TestUserFactory.create(id, email, name, userType, version);
		userRepository.insert(user);
		return user;
	}
}
