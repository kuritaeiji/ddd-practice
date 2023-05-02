package dddpractice.presentation.circle;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import dddpractice.domain.circle.Circle;
import dddpractice.domain.circle.CircleName;
import dddpractice.domain.circle.CircleRepository;
import dddpractice.domain.share.Version;
import dddpractice.domain.user.User;
import dddpractice.factory.circle.TestCircleDataCreator;
import dddpractice.factory.user.TestUserDataCreator;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Transactional
public class CircleCreateControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	TestCircleDataCreator testCircleDataCreator;

	@Autowired
	TestUserDataCreator testUserDataCreator;

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	CircleRepository circleRepository;

	@Test
	void createCircleTest() throws Exception {
		// given（前提条件）
		List<User> users = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			User user = testUserDataCreator.create(null, null, null, null, null);
			users.add(user);
		}
		User owner = users.get(0);
		List<User> members = users.subList(1, users.size());
		List<Long> memberIds = members.stream().map(User::getId).toList();
		CircleCreateRequest request = new CircleCreateRequest("サークルA", owner.getId(), memberIds);
		String json = objectMapper.writeValueAsString(request);

		// when（操作）
		mockMvc.perform(
				post("/api/circle")
						.contentType(MediaType.APPLICATION_JSON)
						.content(json))
				.andExpect(status().is(200));
		Circle circle = circleRepository.findByName(new CircleName(request.getName())).get();

		// then（期待する結果）
		assertThat(circle).isNotNull();
		assertThat(circle.getName()).isEqualTo(new CircleName(request.getName()));
		assertThat(circle.getOwnerId()).isEqualTo(request.getOwnerId());
		assertThat(circle.getMemberIds()).isEqualTo(request.getMemberIds());
		assertThat(circle.getVersion()).isEqualTo(Version.init());
	}
}
