package cn.edu.nju.FindIt.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.nju.FindIt.model.Image;
import cn.edu.nju.FindIt.model.Item;
import cn.edu.nju.FindIt.model.Tag;
import cn.edu.nju.FindIt.repository.ItemRepository;
import cn.edu.nju.FindIt.repository.TagRepository;
import cn.edu.nju.FindIt.utils.DTO.ItemDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ItemServiceImpl implements ItemService {

    private ItemRepository itemRepository;
    private TagRepository tagRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, TagRepository tagRepository) {
        this.itemRepository = itemRepository;
        this.tagRepository = tagRepository;
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "item",key = "#id")
    public Item findItemById(Long id) {
        return itemRepository.findById(id).orElseGet(() -> {
            return new Item();
        });
    }

    @Override
    @Transactional
    public Item saveItem(ItemDTO itemDTO) {
        Item savedItem = new Item();
        savedItem.setDescription(itemDTO.getDescription());
        savedItem.setMethod(itemDTO.getMethod());
        savedItem.setCreatedTime(itemDTO.getCreatedTime());
        savedItem.setCreatedBy(itemDTO.getCreatedBy());
        savedItem.setState(itemDTO.getState());

        List<String> originalTags = itemDTO.getTags();
        List<Tag> newTags = new ArrayList<Tag>();
        for(String tagName: originalTags)
            newTags.add(tagRepository.findByName(tagName));
        savedItem.setTags(newTags);

        List<String> originalImgs = itemDTO.getPhotos();
        List<Image> newImgs = new ArrayList<Image>();
        for(String imgUrl: originalImgs){
            Image tImg = new Image();
            tImg.setUrl(imgUrl);
            newImgs.add(tImg);
        }
        savedItem.setPhotos(newImgs);

        return this.itemRepository.save(savedItem);
    }

    @Override
    @CacheEvict(value = "items", allEntries = true)
    public boolean removeItem(Long id) {
        Optional<Item> item = itemRepository.findById(id);
        item.ifPresent((i) -> {
            i.setState("removed");
            itemRepository.save(i);
        });
        return item.isPresent();
    }

    @Override
    @Cacheable(value = "items")
    public Collection<Item> itemList(int page, Optional<Long> createdBy, Optional<String> description,
            Optional<List<String>> tags) {
        Pageable pageable = PageRequest.of(page, 20, Sort.by("createdTime").descending());
        int state = 0;
        state += createdBy.isPresent()?1:0;
        state += description.isPresent()?2:0;
        state += tags.isPresent()?4:0;
        switch(state){
            case 0: return itemRepository
                            .findAll(pageable)
                            .getContent();
            case 1: return itemRepository
                            .findItemsByCreatedBy(createdBy.get(), pageable)
                            .getContent();
            case 2: return itemRepository
                            .findByDescriptionLike("%" + description.get() + "%", PageRequest.of(page, 20))
                            .getContent();
            case 3: return itemRepository
                            .findItemsCreatedByAndDescriptionLike(description.get(), createdBy.get(), pageable)
                            .getContent();
            case 4: return itemRepository
                            .findItemsInTags(tags.get(), PageRequest.of(page, 20))
                            .getContent();
            case 5: return itemRepository
                            .findItemsInTagsAndCreatedBy(tags.get(), createdBy.get(), PageRequest.of(page, 20))
                            .getContent();
            case 6: return itemRepository
                            .findItemsInTagsAndDescriptionLike(tags.get(), "%" + description.get() + "%", PageRequest.of(page, 20))
                            .getContent();
            case 7: return itemRepository
                            .findItemsInTagsAndCreatedByAndDescriptionLike(tags.get(), description.get(), createdBy.get(), PageRequest.of(page, 20))
                            .getContent();
            default: return null;
        }
    }

    @Override
    @Cacheable(value = "tags")
    public Collection<Tag> findAllTags() {
        return tagRepository.findAll();
    }

    @Override
    public Collection<Item> findItemsToday(int page) {
        return itemRepository.findItemsToday(PageRequest.of(page, 20, Sort.by("createdTime").descending()))
                .getContent();
    }

}
