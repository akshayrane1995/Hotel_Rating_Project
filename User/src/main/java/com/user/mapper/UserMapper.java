package com.user.mapper;

import com.user.dto.UserDto;
import com.user.entity.User;

public class UserMapper {

	public static User mapToUser(UserDto userDto) {
		User user = new User(
					userDto.id(),
					userDto.name(),
					userDto.email()
//					userDto.password()
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
