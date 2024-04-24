package com.dev.usersweb.mapper;

import com.dev.usersweb.data.UserData;
import com.dev.usersweb.enums.UserRole;
import com.dev.usersweb.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "id", expression = "java(userModel.getId().toString())")
    @Mapping(target = "birthDate", dateFormat = "dd-MM-yyyy")
    @Mapping(target = "roles", source = "roles", qualifiedByName = "mapEnumValues")
    public abstract UserData mapUserModel2Data(UserModel userModel);


    @Named("mapEnumValues")
    default List<String> mapEnumValues(List<UserRole> roles) {
        if (CollectionUtils.isEmpty(roles)) {
            return Collections.emptyList();
        }
        return roles.stream().map(UserRole::name).toList();
    }
}
