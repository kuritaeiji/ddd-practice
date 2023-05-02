package dddpractice.infrastructure.user;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import dddpractice.domain.share.Version;
import dddpractice.domain.user.User;
import dddpractice.domain.user.UserRepository;
import dddpractice.domain.user.UserType;
import dddpractice.factory.user.TestUserDataCreator;
import dddpractice.factory.user.TestUserFactory;
import dddpractice.infrastructure.RepositoryTest;
import dddpractice.share.exception.Warning;
import dddpractice.usecase.user.UserCreateCommand;

@RepositoryTest
public class UserRepositoryTest {

	@Autowired
	UserRepository userRepository;

	@Autowired
	TestUserDataCreator testUserDataCreator;

	@Test
	void insertTest() throws Exception {
		// given（前提条件）
		User user = TestUserFactory.create(null, null, null, null, null);
		// when（操作）
		userRepository.insert(user);
		User result = userRepository.findById(user.getId()).get();

		// then（期待する結果）
		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo(user.getId());
		assertThat(result.getEmail()).isEqualTo(user.getEmail());
		assertThat(result.getName()).isEqualTo(user.getName());
		assertThat(result.getType()).isEqualTo(user.getType());
		assertThat(result.getVersion()).isEqualTo(user.getVersion());
	}

	@Test
	void updateThenSuccessTest() throws Exception {
		// given（前提条件）
		User user = testUserDataCreator.create(null, null, null, UserType.STANDARD, null);

		// when（操作）
		user.changeEmail("new@new.com");
		user.changeType(UserType.PREMIUM.getCode());
		userRepository.update(user);
		User result = userRepository.findById(user.getId()).get();

		// then（期待する結果）
		assertThat(result).isNotNull();
		assertThat(result.getEmail()).isEqualTo(user.getEmail());
		assertThat(result.getVersion()).isEqualTo(new Version(user.getVersion().value() + 1));
		assertThat(result.getType()).isEqualTo(user.getType());
	}

	@Test
	void updateThenThrowOptimisticWarningTest() throws Exception {
		// given（前提条件）
		testUserDataCreator.create(null, null, null, null, 2L);

		// when（操作）
		UserCreateCommand command = new UserCreateCommand(
				"aa@aa.com",
				"name",
				UserType.STANDARD.getCode());
		User updateUser = User.create(command);
		Throwable throwable = catchThrowable(() -> {
			userRepository.update(updateUser);
		});

		// then（期待する結果）
		assertThat(throwable).isInstanceOf(Warning.class);
		Warning warning = (Warning) throwable;
		assertThat(warning.getMessage()).isEqualTo("楽観ロックエラー");
	}
}
