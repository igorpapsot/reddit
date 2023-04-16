package rs.ac.uns.ftn.informatika.svtprojekat.service;

import rs.ac.uns.ftn.informatika.svtprojekat.entity.Community;
import rs.ac.uns.ftn.informatika.svtprojekat.entity.Post;
import rs.ac.uns.ftn.informatika.svtprojekat.entity.dto.PostDTO;

import java.io.IOException;
import java.util.List;

public interface PostService {

    List<Post> findAllByParent(Post parent);

    Post findOne(String id);

    List<Post> findAll();

    List<Post> findAllFromCommunity(Community community);

    Post save(Post post);

    void remove(String id);

    void upVote(Integer userId, Post post);

    void downVote(Integer userId, Post post);

    String getId();

    List<Post> findByText(String text);

    List<Post> findByTitle(String name);

    List<Post> findPosts(String input);

    void indexUploadedFile(Post post) throws IOException;
}
