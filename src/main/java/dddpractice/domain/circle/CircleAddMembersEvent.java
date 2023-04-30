package dddpractice.domain.circle;

import java.util.List;

import dddpractice.domain.share.Event;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CircleAddMembersEvent implements Event {
	private final List<Long> memberIds;
}
