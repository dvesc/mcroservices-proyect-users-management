package example.mcroservice.users.repositories;


import example.mcroservice.users.vo.Users_vo;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface Users_repository extends JpaRepository<Users_vo,Long> {
 
  //Email----------------------------------------------------------------------
  @Query(
   value="SELECT * FROM users WHERE discharge_date IS NULL AND email = ?1",
    nativeQuery = true
  )
  Users_vo find_user_by_email(String email);
  

  //First name-----------------------------------------------------------------
  @Query(
    value="SELECT * FROM users WHERE discharge_date IS NULL AND first_name = ?1",
    nativeQuery = true
  )
  Users_vo find_user_by_First_name(String first_name);


  //last name------------------------------------------------------------------
  @Query(
    value="SELECT * FROM users WHERE discharge_date IS NULL AND last_name = ?1",
    nativeQuery = true
  )
  Users_vo find_user_by_last_name(String last_name);

 


  //COINCIDENCIAS EN TODAS LAS COLUMNAS----------------------------------------
  @Query(
    value="SELECT * FROM users "+
          "WHERE "+
            "discharge_date IS NULL "+ 
            "AND last_name LIKE %:filter_value% "+
            "OR first_name LIKE %:filter_value% "+ 
            "OR email LIKE %:filter_value%",
    nativeQuery = true
  )
  Page<Users_vo> find_coincidences_by_all(
    @Param("filter_value") String filter_value,
    Pageable pageable //para que pagine y ordene
  );

  //COINCIDENCIAS EN EMAIL------------------------------------------------------
  @Query(
    value="SELECT * FROM users "+
          "WHERE "+
            "discharge_date IS NULL "+
            "AND email LIKE %:filter_value%",
    nativeQuery = true
  )
  Page<Users_vo> find_coincidences_by_email(
    @Param("filter_value") String filter_value,
    Pageable pageable //para que pagine y ordene
  );

  //COINCIDENCIAS EN FIRST NAME-------------------------------------------------
  @Query(
    value="SELECT * FROM users "+
          "WHERE "+
            "discharge_date IS NULL "+
            "AND first_name LIKE %:filter_value% ",
    nativeQuery = true
  )
  Page<Users_vo> find_coincidences_by_first_name(
    @Param("filter_value") String filter_value,
    Pageable pageable //para que pagine y ordene
  );

  //COINCIDENCIAS EN LAST_NAME--------------------------------------------------
  @Query(
    value="SELECT * FROM users "+
          "WHERE "+
            "discharge_date IS NULL "+
            "AND last_name LIKE %:filter_value%",
    nativeQuery = true
  )
  Page<Users_vo> find_coincidences_by_last_name(
    @Param("filter_value") String filter_value,
    Pageable pageable //para que pagine y ordene
  );

  //ID-------------------------------------------------------------------------
  @Query(
   value="SELECT * FROM users WHERE discharge_date IS NULL AND id = ?1",
    nativeQuery = true
  )
  Users_vo find_user_by_id(Long id);
}
