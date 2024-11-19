package com.srltas.runtogether.adapter.out.persistence.mybatis.dao;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDAO {
	private String id;
	private String name;
	private List<UserNeighborhoodDAO> userNeighborhoodDAOs;
}
