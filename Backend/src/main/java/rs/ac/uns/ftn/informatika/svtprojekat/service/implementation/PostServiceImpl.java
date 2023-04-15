package rs.ac.uns.ftn.informatika.svtprojekat.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.informatika.svtprojekat.entity.Community;
import rs.ac.uns.ftn.informatika.svtprojekat.entity.Post;
import rs.ac.uns.ftn.informatika.svtprojekat.entity.Reaction;
import rs.ac.uns.ftn.informatika.svtprojekat.entity.ReactionTypeENUM;
import rs.ac.uns.ftn.informatika.svtprojekat.repository.PostRepository;
import rs.ac.uns.ftn.informatika.svtprojekat.repository.ReactionRepository;
import rs.ac.uns.ftn.informatika.svtprojekat.service.PostService;
import rs.ac.uns.ftn.informatika.svtprojekat.service.UserService;

import java.time.LocalDate;
import java.util.List;

import static org.elasticsearch.common.UUIDs.base64UUID;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository repository;

    @Autowired
    ReactionRepository reactionRepository;

    @Autowired
    UserService userService;

    @Override
    public List<Post> findAllByParent(Post parent) {
        return null;
    }

    @Override
    public Post findOne(Integer id) { return repository.findById(id).orElse(null); }

    @Override
    public List<Post> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Post> findAllFromCommunity(Community community) {
        return repository.findAllByCommunityId(community.getId());
    }

    @Override
    public Post save(Post post) {
        return repository.save(post);
    }

    @Override
    public void remove(String id) {
        repository.deleteById(id);
    }

    @Override
    public void upVote(Integer userId, Post post) {
        Reaction reaction = new Reaction();
        LocalDate ts = LocalDate.now();

        reaction.setPostId(post.getId());
        reaction.setType(ReactionTypeENUM.UPVOTE);
        reaction.setUser(userService.findOne(userId));
        reaction.setTimestamp(ts);
        reactionRepository.save(reaction);
    }

    @Override
    public void downVote(Integer userId, Post post) {
        Reaction reaction = new Reaction();
        LocalDate ts = LocalDate.now();

        reaction.setPostId(post.getId());
        reaction.setType(ReactionTypeENUM.DOWNVOTE);
        reaction.setUser(userService.findOne(userId));
        reaction.setTimestamp(ts);
        reactionRepository.save(reaction);
    }

    @Override
    public String getId() {
        String id = base64UUID();
        System.out.println(id);
//        if(repository.findDistinctById(id) == null) {
//            return id;
//        }
//        else {
//            return null;
//        }
        return id;


    }

    @Override
    public List<Post> findByText(String text) {
        return repository.findAllByTextContains(text);
    }

    @Override
    public List<Post> findByTitle(String title) {
        return repository.findAllByTitleContains(title);
    }

}
