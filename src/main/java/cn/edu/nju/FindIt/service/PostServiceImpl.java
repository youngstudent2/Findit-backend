package cn.edu.nju.FindIt.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.nju.FindIt.model.Post;
import cn.edu.nju.FindIt.repository.PostRepository;

@Service
public class PostServiceImpl implements PostService{
    PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    @Override
    // 仅缓存不带days和description参数的请求
    @Cacheable(value = "posts")
    public Collection<Post> postList(int page, Optional<Integer> days, Optional<String> description) {
        int state = 0;
        state += days.isPresent()?1:0;
        state += description.isPresent()?2:0;
        switch(state){
            case 0:return postRepository
                            .findAll(PageRequest.of(page, 3, Sort.by("createdTime").descending()))
                            .getContent();
            case 1:return postRepository
                            .findRecent(days.get(), PageRequest.of(page, 20))
                            .getContent();
            case 2:return postRepository
                            .findByContentLikeOrTitleLike(description.get(), PageRequest.of(page, 20))
                            .getContent();
            case 3:return postRepository
                            .findRecentContentLikeOrTitleLike(days.get(), description.get(), PageRequest.of(page, 20))
                            .getContent();
            default: return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Post findPostById(Long id) {
        return postRepository.findById(id).orElseGet(()->{
            return new Post();
        });
    }
}
