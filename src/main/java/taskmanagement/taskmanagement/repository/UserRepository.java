package taskmanagement.taskmanagement.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import taskmanagement.taskmanagement.entity.User;

@Repository
@Qualifier("user")
public interface UserRepository extends JpaRepository<User, Integer> {

}
