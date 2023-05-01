package dddpractice.domain.user;

import java.util.List;
import java.util.Optional;

import dddpractice.domain.share.Email;

public interface UserRepository {

	public Optional<User> findByEmail(Email email);

	public Optional<User> findById(Long id);

	public List<User> findByIds(List<Long> ids);

	public void insert(User user);

	// 楽観ロック
	public void update(User user);
}
