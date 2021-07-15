package cn.edu.nju.FindIt.controller;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.nju.FindIt.model.Item;
import cn.edu.nju.FindIt.model.Tag;
import cn.edu.nju.FindIt.service.ItemService;
import cn.edu.nju.FindIt.utils.DTO.ItemDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.springframework.web.bind.annotation.RequestParam;

@Api(tags={"物品接口"})
@RequestMapping("/api/items")
@RestController
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @ApiOperation(value = "物品详情",notes = "返回物品的详情信息")
    @GetMapping("/{id}")
    public Item itemDetail(@PathVariable("id") Long id) {
        return itemService.findItemById(id);
    }

    @ApiOperation(value = "物品列表",notes = "分页、条件筛选返回物品列表")
    @GetMapping("")
    public Collection<Item> itemList(
            @ApiParam(value = "页数(默认为0)",required = false)
            @RequestParam(value = "page", required = false)
            Optional<Integer> page,

            @ApiParam(value = "创建者ID",required = false)
            @RequestParam(value = "createdBy", required = false)
            Optional<Long> createdBy,

            @ApiParam(value = "描述",required = false)
            @RequestParam(value = "description", required = false)
            Optional<String> description,

            @ApiParam(value = "标签",required = false)
            @RequestParam(value = "tags", required = false)
            Optional<List<String>> tags
            ) {
        return itemService.itemList(page.orElse(0), createdBy, description, tags);
    }

    @ApiOperation(value = "创建物品",notes = "")
    @PostMapping("")
    public Item createItem(@RequestBody ItemDTO newItem) {
        return itemService.saveItem(newItem);
    }

    @GetMapping("/tags")
    public Collection<Tag> tagList() {
        return itemService.findAllTags();
    }

    @ApiOperation(value = "删除物品",notes = "需要带上用户id")
    @DeleteMapping("/{id}")
    public boolean deleteItem(@PathVariable("id") Long id,
            @RequestParam(value = "userid", required = true) Long userid) {
        // TODO: 用 userid 做验证
        return itemService.removeItem(id);
    }

    @GetMapping("today")
    public Collection<Item> todayItemList(
            @ApiParam(value = "页数(默认为0)",required = false)
            @RequestParam(value = "page", required = false)
            Optional<Integer> page){
        return itemService.findItemsToday(page.orElse(0));
    }
}
