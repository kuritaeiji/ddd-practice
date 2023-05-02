package dddpractice.usecase.user;

import org.springframework.stereotype.Service;

import dddpractice.domain.user.User;
import dddpractice.domain.user.UserIsDuplicateDomainService;
import dddpractice.domain.user.UserRepository;
import dddpractice.share.exception.Warning;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserCreateUsecase {

	private final UserRepository userRepository;
	private final UserIsDuplicateDomainService userIsDuplicateDomainService;

	public void execute(UserCreateCommand command) {
		User user = User.create(command);
		if (userIsDuplicateDomainService.execute(user)) {
			throw new Warning("すでに登録されているメールアドレスです");
		}
		userRepository.insert(user);
	}
}
