package dddpractice.usecase.circle;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dddpractice.domain.circle.Circle;
import dddpractice.domain.circle.CircleAppropriateMembersCountDomainService;
import dddpractice.domain.circle.CircleName;
import dddpractice.domain.circle.CircleRepository;
import dddpractice.domain.user.User;
import dddpractice.domain.user.UserRepository;
import dddpractice.share.Pair;
import dddpractice.share.exception.Warning;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CircleAddMembersUsecase {

	private final CircleAppropriateMembersCountDomainService circleAppropriateMembersCountDomainService;
	private final UserRepository userRepository;
	private final CircleRepository circleRepository;

	@Transactional
	public void execute(CircleAddMembersCommand command) {
		Circle circle = circleRepository
				.findByName(new CircleName(command.getName()))
				.orElseThrow(() -> {
					return new Warning("サークルが存在ません");
				});

		List<User> addingMembers = userRepository.findByIds(command.getAddingMemberIds());
		if (addingMembers.size() != command.getAddingMemberIds().size()) {
			throw new Warning("存在しないメンバーがいます");
		}

		circle.addMembers(command.getAddingMemberIds());
		Pair<Boolean, Integer> result = circleAppropriateMembersCountDomainService.execute(circle);
		if (!result.getLeft()) {
			throw new Warning(String.format("サークルのメンバー数は%d人までです", result.getRight()));
		}

		circleRepository.update(circle);
	}
}