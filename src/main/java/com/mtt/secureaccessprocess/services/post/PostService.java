package com.mtt.secureaccessprocess.services.post;

import com.mtt.secureaccessprocess.entities.Post;

import java.util.List;

public interface PostService {
    List<Post> getAllPosts();
    Post getPostById(long id);
    Post createPost(Post post, long userId);
    Post updatePost(long userId, long postId,Post post);
    void deletePost(long id);
}
