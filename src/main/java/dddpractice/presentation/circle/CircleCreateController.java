package dddpractice.presentation.circle;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dddpractice.usecase.circle.CircleCreateCommand;
import dddpractice.usecase.circle.CircleCreateUsecase;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CircleCreateController {

	private final CircleCreateUsecase circleCreateUsecase;

	@PostMapping("/api/circle")
	public void execute(@RequestBody CircleCreateRequest request) {
		CircleCreateCommand command = new CircleCreateCommand(request.getName(), request.getOwnerId(),
				request.getMemberIds());
		circleCreateUsecase.execute(command);
	}
}
