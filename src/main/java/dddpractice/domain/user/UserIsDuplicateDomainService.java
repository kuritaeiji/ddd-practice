package dddpractice.domain.user;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserIsDuplicateDomainService {

	private final UserRepository userRepository;

	public boolean execute(User user) {
		return userRepository.findByEmail(user.getEmail()).isPresent();
	}
}
