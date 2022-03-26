package example.mcroservice.users.mappers;

import example.mcroservice.users.dto.User_in_list_dto;
import example.mcroservice.users.dto.validators.New_user_dto;
//import example.mcroservice.users.dto.validators.Updated_user_dto;
import example.mcroservice.users.vo.Users_vo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface Users_mapper {
  Users_mapper INSTANCE = Mappers.getMapper(Users_mapper.class);

  @Mapping(target = "id", source = "auth0_id")
  @Mapping(target = "phone.phone_number", source = "phone_number")
  @Mapping(target = "phone.country_code", source = "country_code")
  User_in_list_dto user_vo_to_user_in_list_dto(Users_vo vo);
  
  Users_vo new_user_dto_to_vo(New_user_dto dto);
  //New_user_dto new_user_vo_to_dto(Users_vo vo);
  //Updated_user_dto updated_user_vo_to_dto(Users_vo vo);
  //Users_vo updated_user_dto_to_vo(Updated_user_dto dto);

}
