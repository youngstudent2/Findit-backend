package cn.edu.nju.FindIt.service;

import java.util.Collection;
import java.util.Optional;

import cn.edu.nju.FindIt.model.Post;

public interface PostService {
    Collection<Post> postList(int page,Optional<Integer> days, Optional<String> description);

    Post findPostById(Long id);
}
