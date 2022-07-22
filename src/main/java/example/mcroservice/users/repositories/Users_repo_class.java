package example.mcroservice.users.repositories;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import example.mcroservice.users.vo.Users_vo;

public class Users_repo_class implements Users_repository {

  @Override
  public Page<Users_vo> find_coincidences_by_all(String filter_value, Pageable pageable) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Page<Users_vo> find_coincidences_by_email(String filter_value, Pageable pageable) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Page<Users_vo> find_coincidences_by_first_name(String filter_value, Pageable pageable) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Page<Users_vo> find_coincidences_by_last_name(String filter_value, Pageable pageable) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Users_vo find_user_by_First_name(String first_name) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Users_vo find_user_by_email(String email) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Users_vo find_user_by_id(Long id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Users_vo find_user_by_last_name(String last_name) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void deleteAllByIdInBatch(Iterable<Long> ids) {
    // TODO Auto-generated method stub

  }

  @Override
  public void deleteAllInBatch() {
    // TODO Auto-generated method stub

  }

  @Override
  public void deleteAllInBatch(Iterable<Users_vo> entities) {
    // TODO Auto-generated method stub

  }

  @Override
  public List<Users_vo> findAll() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Users_vo> findAll(Sort sort) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public <S extends Users_vo> List<S> findAll(Example<S> example) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public <S extends Users_vo> List<S> findAll(Example<S> example, Sort sort) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Users_vo> findAllById(Iterable<Long> ids) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void flush() {
    // TODO Auto-generated method stub

  }

  @Override
  public Users_vo getById(Long id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Users_vo getOne(Long id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public <S extends Users_vo> List<S> saveAll(Iterable<S> entities) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public <S extends Users_vo> List<S> saveAllAndFlush(Iterable<S> entities) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public <S extends Users_vo> S saveAndFlush(S entity) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Page<Users_vo> findAll(Pageable pageable) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public long count() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public void delete(Users_vo entity) {
    // TODO Auto-generated method stub

  }

  @Override
  public void deleteAll() {
    // TODO Auto-generated method stub

  }

  @Override
  public void deleteAll(Iterable<? extends Users_vo> entities) {
    // TODO Auto-generated method stub

  }

  @Override
  public void deleteAllById(Iterable<? extends Long> ids) {
    // TODO Auto-generated method stub

  }

  @Override
  public void deleteById(Long id) {
    // TODO Auto-generated method stub

  }

  @Override
  public boolean existsById(Long id) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public Optional<Users_vo> findById(Long id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public <S extends Users_vo> S save(S entity) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public <S extends Users_vo> long count(Example<S> example) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public <S extends Users_vo> boolean exists(Example<S> example) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public <S extends Users_vo> Page<S> findAll(Example<S> example, Pageable pageable) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public <S extends Users_vo, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public <S extends Users_vo> Optional<S> findOne(Example<S> example) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Users_vo find_auth0_id(String auth0_id) {
    // TODO Auto-generated method stub
    return null;
  }

}
