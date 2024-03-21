package com.mtt.secureaccessprocess.services.post;

import com.mtt.secureaccessprocess.entities.Post;
import com.mtt.secureaccessprocess.entities.User;
import com.mtt.secureaccessprocess.repository.PostRepository;
import com.mtt.secureaccessprocess.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    @Autowired
    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post getPostById(long id) {
        return postRepository.findById(id).orElse(null);
    }

    @Override
    public Post createPost(Post post, long userId) {
        User user = userRepository.findById(userId).orElse(null); // Fetch the user
        if (user != null) {
            post.setUser(user); // Set the user for the post
            post.setLastUpdatedAt(new Date()); // Set post creation date
            return postRepository.save(post); // Save the post
        }
        return null;
    }

    @Override
    public Post updatePost(long userId, long postId, Post post) {
        // Checks if the post exists and belongs to the user
        Post existingPost = postRepository.findByIdAndUserId(postId, userId).orElse(null);
        if (existingPost != null) {
            existingPost.setText(post.getText());
            existingPost.setLastUpdatedAt(new Date()); // Updates the last update date
            return postRepository.save(existingPost);
        }
        return null;
    }

    @Override
    public void deletePost(long id) {
        Post post = postRepository.findById(id).orElse(null);
        if (post != null) {
            postRepository.delete(post);
        }
    }

}
