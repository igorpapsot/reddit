package rs.ac.uns.ftn.informatika.svtprojekat.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.informatika.svtprojekat.entity.*;
import rs.ac.uns.ftn.informatika.svtprojekat.repository.ReactionRepository;
import rs.ac.uns.ftn.informatika.svtprojekat.service.ReactionService;
import rs.ac.uns.ftn.informatika.svtprojekat.service.UserService;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReactionServiceImpl implements ReactionService {

    @Autowired
    ReactionRepository repository;

    @Autowired
    UserService userService;

    @Override
    public List<Reaction> findAllByParent(Reaction parent) {
        return null;
    }

    @Override
    public Reaction findOne(Integer id) {
        return null;
    }

    @Override
    public List<Reaction> findAll() {
        return null;
    }

    @Override
    public Reaction save(Reaction reaction) {
        repository.save(reaction);
        return reaction;
    }

    @Override
    public void remove(Integer id) {

    }

    @Override
    public boolean checkIfReactionExists(Integer userId, Post post) {
        User user = userService.findOne(userId);
        if(repository.findByUserAndPost(user, post) == null) {
            return  false;
        }
        else  return  true;

    }

    @Override
    public boolean checkIfReactionExists(Integer userId, Comment comment) {
        User user = userService.findOne(userId);
        if(repository.findByUserAndComment(user, comment) == null) {
            return  false;
        }
        else  return  true;

    }

    @Override
    public void upVoteComment(Integer userId, Comment comment) {
        Reaction reaction = new Reaction();
        LocalDate ts = LocalDate.now();

        reaction.setComment(comment);
        reaction.setType(ReactionTypeENUM.UPVOTE);
        reaction.setUser(userService.findOne(userId));
        reaction.setTimestamp(ts);
        repository.save(reaction);
    }

    @Override
    public void downVoteComment(Integer userId, Comment comment) {
        Reaction reaction = new Reaction();
        LocalDate ts = LocalDate.now();

        reaction.setComment(comment);
        reaction.setType(ReactionTypeENUM.DOWNVOTE);
        reaction.setUser(userService.findOne(userId));
        reaction.setTimestamp(ts);
        repository.save(reaction);
    }

    @Override
    public void deleteAllbyComment(Comment comment) {
        repository.deleteAllByComment(comment);
    }

    @Override
    public void undoReaction(Integer userId, Post post) {
        User user = userService.findOne(userId);
        Reaction reaction = repository.findByUserAndPost(user, post);
        repository.deleteById(reaction.getId());
    }

    @Override
    public void undoReaction(Integer userId, Comment comment) {
        User user = userService.findOne(userId);
        Reaction reaction = repository.findByUserAndComment(user, comment);
        repository.deleteById(reaction.getId());
    }

    @Override
    public int getKarma(Post post) {
        List<Reaction> reactions = repository.findAllByPost(post);
        int karma = 0;

        for(Reaction r : reactions) {
            if(r.getType().equals(ReactionTypeENUM.UPVOTE)){
                karma = karma + 1;
            }
            else {
                karma = karma - 1;
            }
        }
        return karma;
    }

    @Override
    public int getCommentKarma(Comment comment) {
        List<Reaction> reactions = repository.findAllByComment(comment);
        int karma = 0;

        for(Reaction r : reactions) {
            if(r.getType().equals(ReactionTypeENUM.UPVOTE)){
                karma = karma + 1;
            }
            else {
                karma = karma - 1;
            }
        }
        return karma;
    }

    @Override
    public void deleteAllbyPost(Post post) {
        repository.deleteAllByPost(post);
    }
}
