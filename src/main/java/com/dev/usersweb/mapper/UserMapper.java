package com.dev.usersweb.mapper;

import com.dev.usersweb.data.UserData;
import com.dev.usersweb.enums.UserRole;
import com.dev.usersweb.model.UserModel;
import org.mapstruct.*;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", expression = "java(userModel.getId().toString())")
    @Mapping(target = "birthDate", dateFormat = "dd-MM-yyyy")
    @Mapping(target = "roles", source = "roles", qualifiedByName = "mapEnumToString")
    @Mapping(target = "password", source = "password", ignore = true)
    public abstract UserData mapModel2Data(UserModel userModel);

    @Mapping(target = "id", source = "id", ignore = true)
    @Mapping(target = "birthDate", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "roles", source = "roles", qualifiedByName = "mapStringToEnum")
    public abstract UserModel mapData2Model(UserData userData);

    @Mapping(target = "id", expression = "java(Long.parseLong(userData.getId()))")
    @Mapping(target = "birthDate", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "roles", source = "roles", qualifiedByName = "mapStringToEnum")
    @Mapping(target = "password", source = "password",
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(@MappingTarget UserModel userModel, UserData userData);


    @Named("mapEnumToString")
    default List<String> mapEnumToString(Set<UserRole> roles) {
        if (CollectionUtils.isEmpty(roles)) {
            return Collections.emptyList();
        }
        return roles.stream().map(UserRole::name).toList();
    }

    @Named("mapStringToEnum")
    default Set<UserRole> mapStringToEnum(List<String> roles) {
        if (CollectionUtils.isEmpty(roles)) {
            return new HashSet<>();
        }
        Set<UserRole> userRoles = new HashSet<>();
        for (String role : roles) {
            try {
                userRoles.add(UserRole.valueOf(role));
            }catch (Exception ignored){}
        }
        return userRoles;
    }
}
