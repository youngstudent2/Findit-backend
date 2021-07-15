package cn.edu.nju.FindIt.utils.DTO;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import cn.edu.nju.FindIt.model.Image;
import cn.edu.nju.FindIt.model.Item;
import cn.edu.nju.FindIt.model.Tag;
import lombok.Data;

@Data
public class ItemDTO {
    private Long id;
    private String description;
    private String method;
    private Timestamp createdTime;
    private Long createdBy;
    private String state;
    private List<String> tags;
    private List<String> photos;

    public ItemDTO(){
        
    }

    public ItemDTO(Item item){
        id = item.getId();
        description = item.getDescription();
        method = item.getMethod();
        createdTime = item.getCreatedTime();
        createdBy = item.getCreatedBy();
        state = item.getState();

        tags = new ArrayList<String>();
        for(Tag tag:item.getTags())
            tags.add(tag.getName());

        photos = new ArrayList<String>();
        for(Image img:item.getPhotos())
            photos.add(img.getUrl());
    }
}
