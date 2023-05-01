package dddpractice.infrastructure.circle;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import dddpractice.domain.circle.Circle;
import dddpractice.domain.circle.CircleRepository;
import dddpractice.domain.share.Version;
import dddpractice.factory.circle.TestCircleDataCreator;
import dddpractice.factory.circle.TestCircleFactory;
import dddpractice.infrastructure.RepositoryTest;

@RepositoryTest
public class CircleRepositoryTest {

	@Autowired
	CircleRepository circleRepository;

	@Autowired
	TestCircleDataCreator testCircleDataCreator;

	@Test
	void insertTest() throws Exception {
		// given（前提条件）
		Circle circle = TestCircleFactory.create(null, null, null, null, null);

		// when（操作）
		circleRepository.insert(circle);
		Circle result = circleRepository.findByName(circle.getName()).get();

		// then（期待する結果）
		assertThat(result.getId()).isEqualTo(circle.getId());
		assertThat(result.getName()).isEqualTo(circle.getName());
		assertThat(result.getOwnerId()).isEqualTo(circle.getOwnerId());
		assertThat(result.getMemberIds()).isEqualTo(circle.getMemberIds());
		assertThat(result.getVersion()).isEqualTo(circle.getVersion());
	}

	@Test
	void updateWhenAddMembersEventThenAddMembersTest() throws Exception {
		// given（前提条件）
		Circle circle = testCircleDataCreator.create(null, null, null, null, null);
		circle.addMembers(new ArrayList<Long>(Arrays.asList(100L, 101L)));

		// when（操作）
		circleRepository.update(circle);
		Circle result = circleRepository.findByName(circle.getName()).get();

		// then（期待する結果）
		assertThat(result.getMemberIds()).isEqualTo(circle.getMemberIds().stream().sorted().toList());
		assertThat(result.getVersion()).isEqualTo(new Version(circle.getVersion().value() + 1));
	}

	@Test
	void sampleTest() throws Exception {
		// given（前提条件）
		Circle circle = testCircleDataCreator.create(null, null, null, null, null);
		circle.changeName("サークル名(変更後）");

		// when（操作）
		circleRepository.update(circle);
		Circle result = circleRepository.findByName(circle.getName()).get();

		// then（期待する結果）
		assertThat(result).isNotNull();
		assertThat(result);
	}
}
