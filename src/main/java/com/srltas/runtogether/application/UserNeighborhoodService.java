package com.srltas.runtogether.application;

import org.springframework.stereotype.Service;

import com.srltas.runtogether.application.exception.NeighborhoodNotFoundException;
import com.srltas.runtogether.application.exception.UserNotFoundException;
import com.srltas.runtogether.application.port.in.AddUserNeighborhood;
import com.srltas.runtogether.application.port.in.AddUserNeighborhoodCommand;
import com.srltas.runtogether.application.port.in.DeleteUserNeighborhood;
import com.srltas.runtogether.application.port.in.DeleteUserNeighborhoodCommand;
import com.srltas.runtogether.domain.model.neighborhood.Neighborhood;
import com.srltas.runtogether.domain.model.neighborhood.NeighborhoodRepository;
import com.srltas.runtogether.domain.model.neighborhood.exception.NeighborhoodNotRegisteredException;
import com.srltas.runtogether.domain.model.user.User;
import com.srltas.runtogether.domain.model.user.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserNeighborhoodService implements AddUserNeighborhood, DeleteUserNeighborhood {

	private final UserRepository userRepository;
	private final NeighborhoodRepository neighborhoodRepository;

	@Override
	public void addNeighborhood(AddUserNeighborhoodCommand command) {
		User user = userRepository.findById(command.userId())
			.orElseThrow(UserNotFoundException::new);
		Neighborhood neighborhood = neighborhoodRepository.findById(command.neighborhoodId())
			.orElseThrow(NeighborhoodNotFoundException::new);

		user.addNeighborhood(neighborhood);
		userRepository.addUserNeighborhood(user.getId(), neighborhood.getId());
	}

	@Override
	public void deleteUserNeighborhood(DeleteUserNeighborhoodCommand command) {
		User user = userRepository.findById(command.userId()).orElseThrow(UserNotFoundException::new);
		try {
			user.deleteNeighborhood(command.neighborhoodId());
		} catch (NeighborhoodNotRegisteredException e) {
			// 등록되지 않은 동네를 삭제하는 경우, 결과적으로 내 동네에 없는 상태이므로 로그는 기록하되 사용자에게는 성공으로 반환합니다.
			log.error(e.getMessage());
		}
		userRepository.deleteUserNeighborhood(user.getId(), command.neighborhoodId());
	}
}
