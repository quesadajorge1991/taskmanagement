package taskmanagement.taskmanagement.repository;

import org.springframework.data.repository.CrudRepository;

import taskmanagement.taskmanagement.entity.Comment;

public interface CommentRepository extends CrudRepository<Comment, Integer> {

}
