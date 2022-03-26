package example.mcroservice.users.services;

import org.springframework.stereotype.Service;

import example.mcroservice.users.repositories.Pass_req_repository;
import example.mcroservice.users.vo.Pass_req_vo;

@Service
public class Pass_req_services {
  private final Pass_req_repository repo;

  //CONSTRUCTOR----------------------------------------------------------------
  public Pass_req_services(Pass_req_repository repo) {
    this.repo = repo;
  }

  //SERVICES-------------------------------------------------------------------
  public void create_pass_req( Pass_req_vo value){
    repo.save(value);
  }

  public void update_a_pass_req(Pass_req_vo value){
    repo.save(value);
  }

  public Pass_req_vo find_by_process_code( String process_code){
    return repo.find_by_process_code(process_code);
  }

}
