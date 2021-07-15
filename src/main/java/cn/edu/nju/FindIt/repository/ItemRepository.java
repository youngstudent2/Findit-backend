package cn.edu.nju.FindIt.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cn.edu.nju.FindIt.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Page<Item> findAll(Pageable pageable);

//     @Query(value = "select * from item where description like :description order by created_time", nativeQuery = true)
    Page<Item> findByDescriptionLike(@Param("description") String description, @Param("pageable") Pageable pageable);

    Page<Item> findItemsByCreatedBy(Long createdBy, Pageable pageable);

    @Query(value = "select * from item i where not exists"
            + "(select * from tag t where t.name in (:tags) and not exists"
            + "(select * from items_tags it where it.item_id = i.id and it.tag_id = t.id))"
            + "order by created_time desc", nativeQuery = true)
    Page<Item> findItemsInTags(@Param("tags") List<String> tags, @Param("pageable") Pageable pageable);

    @Query(value = "select * from item i where description like :description and not exists"
            + "(select * from tag t where t.name in (:tags) and not exists"
            + "(select * from items_tags it where it.item_id = i.id and it.tag_id = t.id))"
            + "order by created_time desc", nativeQuery = true)
    Page<Item> findItemsInTagsAndDescriptionLike(@Param("tags") List<String> tags,
            @Param("description") String description, @Param("pageable") Pageable pageable);

    @Query(value = "select * from item i where created_by = :createdBy and not exists"
            + "(select * from tag t where t.name in (:tags) and not exists"
            + "(select * from items_tags it where it.item_id = i.id and it.tag_id = t.id))"
            + "order by created_time desc", nativeQuery = true)
    Page<Item> findItemsInTagsAndCreatedBy(@Param("tags") List<String> tags, @Param("createdBy") Long createdBy,
            @Param("pageable") Pageable pageable);

    @Query(value = "select * from item i where created_by = :createdBy and description like :description and not exists"
            + "(select * from tag t where t.name in (:tags) and not exists"
            + "(select * from items_tags it where it.item_id = i.id and it.tag_id = t.id))"
            + "order by created_time desc", nativeQuery = true)
    Page<Item> findItemsInTagsAndCreatedByAndDescriptionLike(@Param("tags") List<String> tags,
            @Param("description") String description, @Param("createdBy") Long createdBy,
            @Param("pageable") Pageable pageable);

    @Query(value = "select * from item i where created_by = :createdBy and description like :description ", nativeQuery = true)
    Page<Item> findItemsCreatedByAndDescriptionLike(@Param("description") String description, @Param("createdBy") Long createdBy,
            @Param("pageable") Pageable pageable);

    @Query(value = "select * from item where date(created_time)=curdate()", nativeQuery = true)
    Page<Item> findItemsToday(@Param("pageable") Pageable pageable);

    @Query(value = "select count(*) from items_tags where tag_id = 3", nativeQuery = true)
    Integer todayLostCount();

    @Query(value = "select count(*) from items_tags where tag_id = 2", nativeQuery = true)
    Integer todayFindCount();

    @Query(value = "select count(*) from item i where date(i.created_time)=curdate() and exists"
            + "(select * from items_tags it where it.item_id = i.id and it.tag_id = 3)", nativeQuery = true)
    Integer totalLostCount();

    @Query(value = "select count(*) from item i where date(i.created_time)=curdate() and exists"
            + "(select * from items_tags it where it.item_id = i.id and it.tag_id = 2)", nativeQuery = true)
    Integer totalFindCount();

    @Query(value = "select count(*) from item where item.state = \"finish\"", nativeQuery = true)
    Integer finishCount();
}
