package dddpractice.infrastructure.circle;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import dddpractice.domain.circle.Circle;
import dddpractice.domain.circle.CircleAddMembersEvent;
import dddpractice.domain.circle.CircleName;

@Mapper
public interface CircleMapper {

	public void insert(Circle circle);

	public void insertCircleMembers(Circle circle);

	public void updateCircle(Circle circle);

	public boolean updateVersion(Circle circle);

	public void addCircleMembers(@Param("circle") Circle circle, @Param("event") CircleAddMembersEvent event);

	public Optional<CircleDto> findByName(CircleName name);
}
