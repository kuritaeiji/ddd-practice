package dddpractice.domain.circle;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import dddpractice.domain.user.User;
import dddpractice.domain.user.UserRepository;
import dddpractice.domain.user.UserType;
import dddpractice.factory.circle.TestCircleFactory;
import dddpractice.factory.user.TestUserFactory;
import dddpractice.share.exception.Warning;

public class CircleCanAddMembersCountDomainServiceTest {

	@Mock
	UserRepository userRepository;

	@InjectMocks
	CircleCanAddMembersCountDomainService circleCanAddMembersCountDomainService;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	@DisplayName("プレミアムユーザー数が9人かつ追加するメンバー含むメンバー数が30人の場合、trueを返却する")
	void whenPremiumUserIs9AndMemberCountIs30ThenReturnTrueTest() throws Exception {
		// given（前提条件）
		Long ownerId = 1L;
		User owner = TestUserFactory.create(ownerId, null, null, UserType.STANDARD, null);
		List<User> members = new ArrayList<>();
		List<Long> memberIds = new ArrayList<>();
		for (Long i = 0L; i < 28; i++) {
			if (i >= 0 && i < 9) {
				members.add(TestUserFactory.create(null, null, null, UserType.PREMIUM, null));
			} else {
				members.add(TestUserFactory.create(null, null, null, UserType.STANDARD, null));
			}
			memberIds.add(i);
		}
		Circle circle = TestCircleFactory.create(null, null, 1L, memberIds, null);
		doReturn(members).when(userRepository).findByIds(circle.getMemberIds());
		doReturn(Optional.of(owner)).when(userRepository).findById(circle.getOwnerId());
		List<Long> addingMembers = List.of(1L);

		// when（操作）
		boolean result = circleCanAddMembersCountDomainService.execute(circle, addingMembers);

		// then（期待する結果）
		assertThat(result).isTrue();
	}

	@Test
	@DisplayName("プレミアムユーザー数が9人かつ追加するメンバー数が31人の場合、エラーを送出する")
	void whenPremiumUserIs10AndMemberCountIs30ThenReturnTrueTest() throws Exception {
		// given（前提条件）
		Long ownerId = 1L;
		User owner = TestUserFactory.create(ownerId, null, null, UserType.STANDARD, null);
		List<User> members = new ArrayList<>();
		List<Long> memberIds = new ArrayList<>();
		for (Long i = 0L; i < 28; i++) {
			if (i >= 0 && i < 9) {
				members.add(TestUserFactory.create(null, null, null, UserType.PREMIUM, null));
			} else {
				members.add(TestUserFactory.create(null, null, null, UserType.STANDARD, null));
			}
			memberIds.add(i);
		}
		Circle circle = TestCircleFactory.create(null, null, 1L, memberIds, null);
		doReturn(members).when(userRepository).findByIds(circle.getMemberIds());
		doReturn(Optional.of(owner)).when(userRepository).findById(circle.getOwnerId());
		List<Long> addingMembers = List.of(1L, 2L);

		// when（操作）
		Throwable throwable = catchThrowable(() -> {
			circleCanAddMembersCountDomainService.execute(circle, addingMembers);
		});

		// then（期待する結果）
		assertThat(throwable).isInstanceOf(Warning.class);
	}
}
