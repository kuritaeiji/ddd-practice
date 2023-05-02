package dddpractice.domain.circle;

import java.util.List;

import dddpractice.domain.share.EventEntity;
import dddpractice.domain.share.Version;
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
	private final Version version;

	public static Circle reconstruct(
			Long id,
			CircleName name,
			Long ownerId,
			List<Long> memberIds,
			Version version) {

		return new Circle(id, name, ownerId, memberIds, version);
	}

	public static Circle create(String name, Long ownerId, List<Long> memberIds, Version version) {
		return new Circle(null, new CircleName(name), ownerId, memberIds, version);
	}

	public void changeName(String name) {
		this.name = new CircleName(name);
		this.addEvent(new CircleUpdateEvent());
	}

	public void addMembers(List<Long> memberIds) {
		this.memberIds.addAll(memberIds);
		this.addEvent(new CircleAddMembersEvent(memberIds));
	}

	public Integer memberCount() {
		// オーナー + メンバーの数
		return 1 + memberIds.size();
	}
}
