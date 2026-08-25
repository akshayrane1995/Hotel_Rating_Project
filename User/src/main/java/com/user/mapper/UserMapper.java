package com.user.mapper;

import com.user.dto.UserCreateDto;
import com.user.dto.UserDto;
import com.user.entity.User;

public class UserMapper {

	public static User mapToUser(UserCreateDto userCreateDto) {
		User user = new User(
				null,
				userCreateDto.name(),
				userCreateDto.email(),
				userCreateDto.password()
				);
		return user;
	}
	
	public static UserDto maptoUserDto(User user){
		UserDto userDto = new UserDto(
				user.getId(),
				user.getName(),
				user.getEmail()
//				user.getPassword()
				);
		return userDto;
	}
}
