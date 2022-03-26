package example.mcroservice.users.dto;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;

import example.mcroservice.users.mappers.Users_mapper;
import example.mcroservice.users.vo.Users_vo;

public class Get_method_response {
  private  List <User_in_list_dto> data;  
  private Metadata_response meta;


  //CONSTRUCTOR----------------------------------------------------------------
  public Get_method_response(
  HttpServletRequest request,
  Page<Users_vo>page_coincidences) {
    List <User_in_list_dto> data_dto = new ArrayList<User_in_list_dto>();
    //Por cada user_vo transformamos a dto  
    for (Users_vo vo : page_coincidences.getContent()) {
      User_in_list_dto dto = Users_mapper.INSTANCE.user_vo_to_user_in_list_dto(vo);
      data_dto.add(dto);
    }

    this.data = data_dto;
    this.meta = new Metadata_response(
      request,
      page_coincidences.getNumber()+1, //pagina actual
      page_coincidences.getSize(), // tamaño de la pagina
      page_coincidences.getTotalElements(), // total de elementos en la consulta
      page_coincidences.getTotalPages() //total de paginas
    );
  }


  //GETTERS AND SETTERS--------------------------------------------------------
  public List<User_in_list_dto> getData() {
    return data;
  }


  public void setData(List<User_in_list_dto> data) {
    this.data = data;
  }


  public Metadata_response getMeta() {
    return meta;
  }


  public void setMeta(Metadata_response meta) {
    this.meta = meta;
  }

 
  
}
