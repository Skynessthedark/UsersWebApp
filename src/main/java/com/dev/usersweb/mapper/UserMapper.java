package com.dev.usersweb.mapper;

import com.dev.usersweb.data.UserData;
import com.dev.usersweb.enums.UserRole;
import com.dev.usersweb.facade.impl.UserFacadeImpl;
import com.dev.usersweb.model.UserModel;
import org.mapstruct.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", expression = "java(userModel.getId().toString())")
    @Mapping(target = "birthDate", dateFormat = "dd-mm-yyyy")
    @Mapping(target = "roles", source = "roles", qualifiedByName = "mapEnumToString")
    @Mapping(target = "password", source = "password", ignore = true)
    //@Mapping(target = "admin", qualifiedByName = "checkAdmin")
    public abstract UserData mapModel2Data(UserModel userModel);

    @Mapping(target = "id", source = "id", ignore = true)
    @Mapping(target = "birthDate", dateFormat = "dd-mm-yyyy")
    @Mapping(target = "roles", source = "roles", ignore = true)
    public abstract UserModel mapData2Model(UserData userData);

    @Mapping(target = "roles", source = "roles", ignore = true)
    @Mapping(target = "id", expression = "java(Long.parseLong(userData.getId()))")
    @Mapping(target = "birthDate", dateFormat = "dd-mm-yyyy")
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

    @Named("checkAdmin")
    default boolean checkAdmin(UserModel userModel) {
        return userModel.getRoles().contains(UserRole.ADMIN);
    }
}
