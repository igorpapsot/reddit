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
import rs.ac.uns.ftn.informatika.svtprojekat.repository.CommunityRepository;
import rs.ac.uns.ftn.informatika.svtprojekat.repository.PostRepository;
import rs.ac.uns.ftn.informatika.svtprojekat.service.CommunityService;
import rs.ac.uns.ftn.informatika.svtprojekat.service.ReactionService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.elasticsearch.common.UUIDs.base64UUID;

@Service
public class CommunityServiceImpl implements CommunityService {

    @Autowired
    CommunityRepository repository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    ReactionService reactionService;

    @Value("${files.path}")
    private String filesPath;

    @Override
    public List<Community> findAllByParent(Community parent) {
        return null;
    }

    @Override
    public Community findOne(String id) {
        return repository.findById(id);
    }

    @Override
    public List<Community> findAll() {
        return repository.findAll();
    }

    @Override
    public Community save(Community community) {
        return repository.save(community);
    }

    @Override
    public void remove(String id) {
        repository.deleteById(id);
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
    public List<Community> findByText(String text) {
        return repository.findAllByDescriptionContains(text);
    }

    @Override
    public List<Community> findByName(String name) {
        return repository.findAllByNameContains(name);
    }

    @Override
    public Integer getNumOfPosts(String communityId) {
        List<Post> posts = postRepository.findAllByCommunityId(communityId);
        return posts.size();
    }

    @Override
    public List<Community> findCommunities(String input) {
        return repository.findCommunityByDescriptionContainsOrNameContains(input, input);
    }

    @Override
    public Integer getKarma(String communityId) {
        List<Post> posts = postRepository.findAllByCommunityId(communityId);
        Integer karma = 0;

        for(Post p : posts) {
            karma = karma + reactionService.getKarma(p);
        }
        return karma;
    }

    @Override
    public void indexUploadedFile(Community community) throws IOException {
        for (MultipartFile file : community.getFiles()) {
            if (file.isEmpty()) {
                continue;
            }

            String fileName = saveUploadedFileInFolder(file);
            if(fileName != null){
                Community postIndexUnit = new PDFHandler().getIndexUnitCommuntiy(new File(fileName));
                postIndexUnit.setId(community.getId());
                postIndexUnit.setName(community.getName());
                postIndexUnit.setDescription(community.getDescription());
                postIndexUnit.setCreationDate(community.getCreationDate());
                postIndexUnit.setSuspended(community.isSuspended());
                repository.save(postIndexUnit);
            }
        }
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
