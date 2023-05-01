package dddpractice.factory.circle;

import java.util.List;

import org.springframework.stereotype.Component;

import dddpractice.domain.circle.Circle;
import dddpractice.domain.circle.CircleRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TestCircleDataCreator {

	private final CircleRepository circleRepository;

	public Circle create(Long id, String name, Long ownerId, List<Long> memberIds, Long version) {
		Circle circle = TestCircleFactory.create(id, name, ownerId, memberIds, version);
		circleRepository.insert(circle);
		return circle;
	}
}
