package dddpractice.infrastructure.circle;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import dddpractice.domain.circle.Circle;
import dddpractice.domain.circle.CircleAddMembersEvent;
import dddpractice.domain.circle.CircleName;
import dddpractice.domain.circle.CircleRepository;
import dddpractice.domain.circle.CircleUpdateEvent;
import dddpractice.domain.share.Event;
import dddpractice.domain.share.Version;
import dddpractice.share.exception.Warning;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CircleRepositoryImpl implements CircleRepository {

	private final CircleMapper circleMapper;

	public Optional<Circle> findByName(CircleName name) {
		Optional<CircleDto> optionalCircle = circleMapper.findByName(name);
		if (optionalCircle.isEmpty()) {
			return Optional.empty();
		}

		return Optional.of(mapToEntity(optionalCircle.get()));
	}

	public void insert(Circle circle) {
		circleMapper.insert(circle);
		circleMapper.insertCircleMembers(circle);
	}

	public void update(Circle circle) {
		List<Event> events = circle.getEvents();
		for (Event event : events) {
			if (event instanceof CircleAddMembersEvent) {
				CircleAddMembersEvent circleAddMembersEvent = (CircleAddMembersEvent) event;
				circleMapper.addCircleMembers(circle, circleAddMembersEvent);
				continue;
			}

			if (event instanceof CircleUpdateEvent) {
				circleMapper.updateCircle(circle);
			}
		}
		boolean canUpdate = circleMapper.updateVersion(circle);
		if (!canUpdate) {
			throw new Warning("楽観ロックエラー");
		}
	}

	private Circle mapToEntity(CircleDto circleDto) {
		return Circle.reconstruct(
				circleDto.getId(),
				new CircleName(circleDto.getName()),
				circleDto.getOwnerId(),
				circleDto.getMemberIds(),
				new Version(circleDto.getVersion()));
	}
}
