package cn.edu.nju.FindIt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cn.edu.nju.FindIt.model.User;

public interface UserRepository extends JpaRepository<User,Long>{
    User getUserByNJUID(String NJUID);

    @Query(value = "select count(*) from user", nativeQuery = true)
    Integer userCount();
}
