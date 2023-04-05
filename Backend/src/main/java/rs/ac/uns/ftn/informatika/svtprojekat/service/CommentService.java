package rs.ac.uns.ftn.informatika.svtprojekat.service;

import rs.ac.uns.ftn.informatika.svtprojekat.entity.Comment;
import rs.ac.uns.ftn.informatika.svtprojekat.entity.Post;

import java.util.List;

public interface CommentService {

    List<Comment> findAllByParent(Comment parent);

    Comment findOne(Integer id);

    List<Comment> findAll();

    Comment save(Comment comment);

    void remove(Integer id);

    List<Comment> findAllByPost(Post post);

    void deleteAllByPost(Post post);

}
