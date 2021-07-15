package cn.edu.nju.FindIt.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import cn.edu.nju.FindIt.model.Item;
import cn.edu.nju.FindIt.model.Tag;
import cn.edu.nju.FindIt.utils.DTO.ItemDTO;

public interface ItemService {
    Item findItemById(Long id);

    Collection<Item> itemList(int page, Optional<Long> createdBy, Optional<String> description, Optional<List<String>> tags);

    Collection<Item> findItemsToday(int page);

    Item saveItem(ItemDTO item);

    boolean removeItem(Long id);

    Collection<Tag> findAllTags();

    // Collection<Item> findAll(int page);

    // Collection<Item> findByCreatedBy(Long createdBy, int page);

    // Collection<Item> findItemsByDescription(String description, int page);

    // Collection<Item> findItemsByTag(String tag);
}
