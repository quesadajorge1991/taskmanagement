package taskmanagement.taskmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import taskmanagement.taskmanagement.entity.Authority;
import taskmanagement.taskmanagement.entity.GroupAuthorities;

public interface GroupAuthoritiesRepository extends JpaRepository<GroupAuthorities, Integer> {
	
	@Query(value = "DELETE FROM GroupAuthorities ga WHERE ga.groupId.id=?1")
	void deleteByGroupId(int id,String authority);
	
	List<GroupAuthorities> findByAuthority(String authority);
	
	
	@Query(value = "SELECT authority FROM GroupAuthorities WHERE groupId.id=?1")
	List<String> findGroupAuthorities(int idgroup);
	
	@Query(value = "FROM GroupAuthorities WHERE groupId.id=?1")
	List<GroupAuthorities> findByGroupId(int idgroup);
	
	
	

}
