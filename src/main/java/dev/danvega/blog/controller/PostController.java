package dev.danvega.blog.controller;

import dev.danvega.blog.model.Post;
import dev.danvega.blog.model.dto.PostDetails;
import dev.danvega.blog.repository.AuthorRepository;
import dev.danvega.blog.repository.PostRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostRepository posts;
    private final AuthorRepository authors;

    public PostController(PostRepository postRepository, AuthorRepository authorRepository) {
        this.posts = postRepository;
        this.authors = authorRepository;
    }

    @GetMapping
    public Iterable<Post> findAll() {
        return posts.findAll();
    }

    @GetMapping("/{id}")
    public Post findById(@PathVariable("id") Post post) {
        return post;
    }

    @GetMapping("/{id}/details")
    public PostDetails getPostDetails(@PathVariable("id") Post post) {
        return new PostDetails(post,authors.findById(post.getAuthor().getId()).get());
    }

}
