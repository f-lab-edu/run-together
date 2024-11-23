package com.srltas.runtogether.adapter.out.persistence.mybatis.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserDTO {
	private String id;
	private String name;
	private List<UserNeighborhoodDTO> userNeighborhoodDTOs;
}
