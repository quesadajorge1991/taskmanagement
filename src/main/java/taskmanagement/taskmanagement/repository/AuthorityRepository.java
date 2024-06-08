package taskmanagement.taskmanagement.repository;

import org.springframework.data.repository.CrudRepository;

import taskmanagement.taskmanagement.entity.Authority;
import taskmanagement.taskmanagement.entity.AuthorityPK;

public interface AuthorityRepository extends CrudRepository<Authority, AuthorityPK> {

}
