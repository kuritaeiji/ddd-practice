package dddpractice.usecase.circle;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dddpractice.domain.circle.Circle;
import dddpractice.domain.circle.CircleAppropriateMembersCountDomainService;
import dddpractice.domain.circle.CircleIsDuplicateDomainService;
import dddpractice.domain.circle.CircleRepository;
import dddpractice.domain.user.User;
import dddpractice.domain.user.UserRepository;
import dddpractice.share.Pair;
import dddpractice.share.exception.Warning;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CircleCreateUsecase {

	private final CircleIsDuplicateDomainService circleIsDuplicateDomainService;
	private final CircleAppropriateMembersCountDomainService circleAppropriateMembersCountDomainService;
	private final UserRepository userRepository;
	private final CircleRepository circleRepository;

	@Transactional
	public void execute(CircleCreateCommand command) {
		Optional<User> owner = userRepository.findById(command.getOwnerId());
		if (owner.isEmpty()) {
			throw new Warning("オーナーが見つかりません");
		}

		List<User> members = userRepository.findByIds(command.getMemberIds());
		if (members.size() != command.getMemberIds().size()) {
			throw new Warning("存在しないメンバーがいます");
		}

		Circle circle = Circle.create(command);
		if (circleIsDuplicateDomainService.execute(circle)) {
			throw new Warning("すでに登録されているサークル名です");
		}

		Pair<Boolean, Integer> result = circleAppropriateMembersCountDomainService.execute(circle);
		if (!result.getLeft()) {
			throw new Warning(String.format("サークル内のメンバーは%d人までです", result.getRight()));
		}

		circleRepository.insert(circle);
	}
}
