package com.user.service;

import java.util.List;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.user.dto.UserDto;
import com.user.entity.User;
import com.user.exception.ResourceNotFoundException;
import com.user.mapper.UserMapper;
import com.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDto createUser(UserDto userDto) {
		User user = UserMapper.mapToUser(userDto);
		User saveuser = userRepository.save(user);
		return UserMapper.maptoUserDto(saveuser);
	}

	@Override
	public UserDto getUserById(Long id) {
		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("user does not exist"));
		return UserMapper.maptoUserDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = userRepository.findAll();
		return users.stream().map((user) -> UserMapper.maptoUserDto(user)).collect(Collectors.toList());
	}

	@Override
	public UserDto updateUser(Long id, UserDto userDto) {

		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User does not exist"));

		user.setName(userDto.name());
		user.setEmail(userDto.email());
//		user.setPassword(userDto.password());
		
		User updatedUser = userRepository.save(user);
		return UserMapper.maptoUserDto(updatedUser);
	}

	@Override
	public void deleteUser(Long id) {
		if(!userRepository.existsById(id)) {
			throw new ResourceNotFoundException("User does not exist");
		}
		userRepository.deleteById(id);
	}

}
