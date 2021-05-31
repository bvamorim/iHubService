package com.ihub.api.databuilder;

import com.ihub.api.model.datatransferobject.UserDto;

/**
 * <h1>UserDtoDataBuilder</h1>
 * 
 * @author Bruno Amorim
 * @version 1.0
 * @since 2021-05-30
 */
public class UserDtoDataBuilder {

    public static UserDto getDefaultUser(){

        return new UserDto("teste","password", "teste@" );
    }

}
