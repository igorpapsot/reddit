package rs.ac.uns.ftn.informatika.svtprojekat.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import rs.ac.uns.ftn.informatika.svtprojekat.entity.Community;
import rs.ac.uns.ftn.informatika.svtprojekat.entity.Post;
import rs.ac.uns.ftn.informatika.svtprojekat.entity.Reaction;
import rs.ac.uns.ftn.informatika.svtprojekat.entity.ReactionTypeENUM;
import rs.ac.uns.ftn.informatika.svtprojekat.lucene.indexing.handlers.PDFHandler;
import rs.ac.uns.ftn.informatika.svtprojekat.repository.PostRepository;
import rs.ac.uns.ftn.informatika.svtprojekat.repository.ReactionRepository;
import rs.ac.uns.ftn.informatika.svtprojekat.service.PostService;
import rs.ac.uns.ftn.informatika.svtprojekat.service.UserService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    @Value("${files.path}")
    private String filesPath;

    @Override
    public List<Post> findAllByParent(Post parent) {
        return null;
    }

    @Override
    public Post findOne(String id) { return repository.findById(id); }

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
        return repository.findAllByText(text);
    }

    @Override
    public List<Post> findByTitle(String title) {
        return repository.findAllByTitle(title);
    }

    @Override
    public List<Post> findPosts(String input) {
        return repository.findPostByTextOrTitle(input, input);
    }

    @Override
    public void indexUploadedFile(Post post) throws IOException {
        for (MultipartFile file : post.getFiles()) {
            if (file.isEmpty()) {
                continue;
            }

            String fileName = saveUploadedFileInFolder(file);
            if(fileName != null){
                Post postIndexUnit = new PDFHandler().getIndexUnitPost(new File(fileName));
                postIndexUnit.setTitle(post.getTitle());
                postIndexUnit.setText(post.getText());
                postIndexUnit.setCommunityId(post.getCommunityId());
                postIndexUnit.setFlair(post.getFlair());
                postIndexUnit.setUserId(post.getUserId());
                postIndexUnit.setCreationDate(post.getCreationDate());
                postIndexUnit.setId(post.getId());
                repository.save(postIndexUnit);
            }
        }
    }

    @Override
    public List<Post> findByPdfText(String text) {
        return repository.findPostByPdfText(text);
    }

    private String saveUploadedFileInFolder(MultipartFile file) throws IOException {
        String retVal = null;
        if (!file.isEmpty()) {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(new File(filesPath).getAbsolutePath() + File.separator + file.getOriginalFilename());
            Files.write(path, bytes);
            retVal = path.toString();
        }
        return retVal;
    }

}
