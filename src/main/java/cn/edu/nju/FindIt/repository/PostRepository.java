package cn.edu.nju.FindIt.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cn.edu.nju.FindIt.model.Post;
import io.lettuce.core.dynamic.annotation.Param;

public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findAll(Pageable pageable);

    @Query(value = "select * from post where datediff(created_time, now()) <= 0 and datediff(created_time, now()) > -:days "
            + "order by created_time desc", nativeQuery = true)
    Page<Post> findRecent(@Param("days") Integer days , @Param("pageable") Pageable pageable);

    @Query(value = "select * from post where datediff(created_time, now()) <= 0 and datediff(created_time, now()) > -:days "
    + "and (title like :description or content like :description)"
    + "order by created_time desc", nativeQuery = true)
    Page<Post> findRecentContentLikeOrTitleLike(@Param("days") Integer days, @Param("description") String description, @Param("pageable") Pageable pageable);

    @Query(value = "select * from post where title like :description or content like :description"
    + "order by created_time desc", nativeQuery = true)
    Page<Post> findByContentLikeOrTitleLike(String description, Pageable pageable);
}
