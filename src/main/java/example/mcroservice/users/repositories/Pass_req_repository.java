package example.mcroservice.users.repositories;

import org.springframework.stereotype.Repository;

import example.mcroservice.users.vo.Pass_req_vo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface Pass_req_repository extends JpaRepository<Pass_req_vo,Long> {
  
  @Query(
    value="SELECT * FROM password_change_requests "+
          "WHERE process_code = :process_code ",
     nativeQuery = true
   )
  Pass_req_vo find_by_process_code (
    @Param("process_code") String process_code);
  

  //ESTE QUERY LO HICE PARA NADA PORQUE AL FINAL SE PUEDE ACCEDER DE OTRA FORMA X'D
 /* @Query(
    value = "SELECT users.auth0_id "+
            "FROM password_change_requests "+
            "JOIN users ON users.id = user_id "+
            "WHERE user_id = :user_id "+
            "LIMIT 0,1",
    nativeQuery = true
  )
  Auth0_id_pass_req_vo find_auth0_id_by_user_id (
   @Param("user_id") String user_id);

*/
}
