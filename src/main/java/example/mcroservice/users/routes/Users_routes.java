package example.mcroservice.users.routes;

import example.mcroservice.users.dto.Client_response_dto;
import example.mcroservice.users.dto.Get_method_response;
import example.mcroservice.users.dto.validators.Change_password_dto;
import example.mcroservice.users.dto.validators.Forgot_password_dto;
import example.mcroservice.users.dto.validators.New_user_dto;
import example.mcroservice.users.dto.validators.Updated_user_dto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequestMapping(value = "user")
public interface Users_routes {

  // METODOS CRUD-------------------------------------------------------------

  // Token required
  @PostMapping("/singup")
  ResponseEntity<Client_response_dto> create_a_user(
      @Valid @RequestBody New_user_dto body);

  @GetMapping
  ResponseEntity<Get_method_response> get_users(
      @RequestParam(value = "filterby", required = false) String filterby,
      @RequestParam(value = "filtervalue", required = false) String filtervalue,
      @RequestParam(value = "orderby", required = false) String orderby,
      @RequestParam(value = "order", required = false) String order,
      @RequestParam(value = "page", required = false) Integer page,
      @RequestParam(value = "size", required = false) Integer size,
      HttpServletRequest request

  );

  // Token Required
  @PutMapping("/{user_id}")
  ResponseEntity<Client_response_dto> update_a_user(
      @PathVariable Long user_id,
      @Valid @RequestBody Updated_user_dto body);

  @DeleteMapping("/{user_id}")
  ResponseEntity<Client_response_dto> delete_a_user(
      @PathVariable Long user_id);

  @PostMapping("/forgotpassword")
  ResponseEntity<Client_response_dto> send_email(
      @Valid @RequestBody Forgot_password_dto body);

  @PatchMapping("/newpassword")
  ResponseEntity<Client_response_dto> new_password(
      @RequestParam(value = "process_code", required = true) String process_code,
      @Valid @RequestBody Change_password_dto body);
}
