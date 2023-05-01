package dddpractice.infrastructure.user;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import dddpractice.domain.share.Email;
import dddpractice.domain.user.User;

@Mapper
public interface UserMapper {

	public Optional<UserDto> findByEmail(Email email);

	public Optional<UserDto> findById(Long id);

	public List<UserDto> findByIds(List<Long> ids);

	public void insert(User user);

	// 楽観ロック
	public boolean update(User user);
}
