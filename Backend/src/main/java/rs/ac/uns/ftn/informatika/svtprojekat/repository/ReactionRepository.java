package rs.ac.uns.ftn.informatika.svtprojekat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.informatika.svtprojekat.entity.Comment;
import rs.ac.uns.ftn.informatika.svtprojekat.entity.Post;
import rs.ac.uns.ftn.informatika.svtprojekat.entity.Reaction;
import rs.ac.uns.ftn.informatika.svtprojekat.entity.User;

import java.util.List;

@Repository
public interface ReactionRepository extends JpaRepository<Reaction, Integer> {

    Reaction findByUserAndPostId(User user, String postId);

    Reaction findByUserAndComment(User user, Comment comment);

    List<Reaction> findAllByPostId(String postId);

    List<Reaction> findAllByComment(Comment comment);

    List<Reaction> findAllByUser(User user);

    void deleteAllByPostId(String postId);

    void deleteAllByComment(Comment comment);
}
