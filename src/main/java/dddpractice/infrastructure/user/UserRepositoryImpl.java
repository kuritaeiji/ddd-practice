package dddpractice.infrastructure.user;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import dddpractice.domain.share.Email;
import dddpractice.domain.share.Version;
import dddpractice.domain.user.User;
import dddpractice.domain.user.UserName;
import dddpractice.domain.user.UserRepository;
import dddpractice.domain.user.UserType;
import dddpractice.share.exception.Warning;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

	private final UserMapper userMapper;

	public Optional<User> findByEmail(Email email) {
		Optional<UserDto> optionalUser = userMapper.findByEmail(email);
		if (optionalUser.isEmpty()) {
			return Optional.empty();
		}

		return Optional.of(mapToEntity(optionalUser.get()));
	}

	public Optional<User> findById(Long id) {
		Optional<UserDto> optionalUser = userMapper.findById(id);
		if (optionalUser.isEmpty()) {
			return Optional.empty();
		}

		return Optional.of(mapToEntity(optionalUser.get()));
	}

	public List<User> findByIds(List<Long> ids) {
		List<UserDto> users = userMapper.findByIds(ids);
		return users.stream().map(userDto -> mapToEntity(userDto)).toList();
	}

	public void insert(User user) {
		userMapper.insert(user);
	}

	// 楽観ロック
	public void update(User user) {
		boolean canUpdate = userMapper.update(user);
		if (!canUpdate) {
			throw new Warning("楽観ロックエラー");
		}
	}

	private User mapToEntity(UserDto userDto) {
		return User.reconstruct(
				userDto.getId(),
				new Email(userDto.getEmail()),
				new UserName(userDto.getName()),
				UserType.fromCode(userDto.getType()),
				new Version(userDto.getVersion()));
	}
}
