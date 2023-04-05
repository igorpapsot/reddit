package rs.ac.uns.ftn.informatika.svtprojekat.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.informatika.svtprojekat.entity.Comment;
import rs.ac.uns.ftn.informatika.svtprojekat.entity.Post;
import rs.ac.uns.ftn.informatika.svtprojekat.repository.CommentRepository;
import rs.ac.uns.ftn.informatika.svtprojekat.service.CommentService;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository repository;

    @Override
    public List<Comment> findAllByParent(Comment parent) {
        return null;
    }

    @Override
    public Comment findOne(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Comment> findAll() {
        return null;
    }

    @Override
    public Comment save(Comment comment) {
         repository.save(comment);
         return comment;
    }

    @Override
    public void remove(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public List<Comment> findAllByPost(Post post) {
        return repository.findAllByPost(post);
    }

    @Override
    public void deleteAllByPost(Post post) {
        repository.deleteByPost(post);
    }
}
