package cn.edu.nju.FindIt.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cn.edu.nju.FindIt.model.Tag;


public interface TagRepository extends JpaRepository<Tag,Long> {
    Tag findByName(String name);
    List<Tag> findByNameIn(List<String> names);

}
