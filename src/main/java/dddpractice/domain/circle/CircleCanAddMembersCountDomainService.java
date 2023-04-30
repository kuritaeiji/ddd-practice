package dddpractice.domain.circle;

import java.util.List;

import org.springframework.stereotype.Component;

import dddpractice.domain.user.User;
import dddpractice.domain.user.UserRepository;
import dddpractice.domain.user.UserType;
import dddpractice.share.exception.Fatal;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CircleCanAddMembersCountDomainService {

	private static final Integer PREMIUM_CIRCLE_MAX_MEMBER_COUNT = 50;
	private static final Integer STANDARD_CIRCLE_MAX_MEMBER_COUNT = 30;
	private static final Integer PREMIUM_CIRCLE_MEMBER_COUNT_LOWER_LIMIT = 10;

	private final UserRepository userRepository;

	/**
	 * サークルに追加できるメンバー数を返却する
	 * 
	 * @param circle
	 * @return
	 */
	public Integer execute(Circle circle) {
		User owner = userRepository
				.findById(circle.getOwnerId())
				.orElseThrow(() -> new Fatal("ユーザーが見つかりません"));
		List<User> members = userRepository.findByIds(circle.getMemberIds());

		Integer premiumMemberCount = owner.getType().equals(UserType.PREMIUM) ? 1 : 0;
		premiumMemberCount += members
				.stream()
				.filter((member) -> member.getType().equals(UserType.PREMIUM))
				.toList()
				.size();

		Integer upperLimit = premiumMemberCount >= PREMIUM_CIRCLE_MEMBER_COUNT_LOWER_LIMIT
				? PREMIUM_CIRCLE_MAX_MEMBER_COUNT
				: STANDARD_CIRCLE_MAX_MEMBER_COUNT;

		return upperLimit - 1 - members.size();
	}
}
