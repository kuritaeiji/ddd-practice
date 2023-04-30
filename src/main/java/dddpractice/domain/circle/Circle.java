package dddpractice.domain.circle;

import java.util.List;

import dddpractice.domain.share.EventEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Circle extends EventEntity {

	private final Long id;
	private CircleName name;
	private Long ownerId;
	private List<Long> memberIds;

	public static Circle reconstruct(
			Long id,
			CircleName name,
			Long ownerId,
			List<Long> memberIds) {

		return new Circle(id, name, ownerId, memberIds);
	}

	public static Circle create(String name, Long ownerId, List<Long> memberIds) {
		return new Circle(null, new CircleName(name), ownerId, memberIds);
	}

	public void changeName(String name) {
		this.name = new CircleName(name);
	}

	public void addMembers(List<Long> memberIds) {
		this.memberIds.addAll(memberIds);
		this.addEvent(new CircleAddMembersEvent(memberIds));
	}
}
