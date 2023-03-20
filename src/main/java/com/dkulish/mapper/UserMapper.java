package com.dkulish.mapper;

import com.dkulish.data.User;
import com.dkulish.entity.UserDb;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserDb toEntity(User user);

    User toDto(UserDb userDb);
}
