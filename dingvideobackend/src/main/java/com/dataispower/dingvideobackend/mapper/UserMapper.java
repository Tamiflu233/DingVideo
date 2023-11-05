package com.dataispower.dingvideobackend.mapper;

import com.dataispower.dingvideobackend.dto.UserIndexResponse;
import com.dataispower.dingvideobackend.dto.UserResponse;
import com.dataispower.dingvideobackend.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * author:heroding
 * date:2023/11/1 14:31
 * description：用户相关信息类自动转换
 **/
@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    UserResponse userToUserResponse(User user);
    UserIndexResponse userToUserIndexResponse(User user);

}
