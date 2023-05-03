package rs.ac.uns.ftn.informatika.svtprojekat.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.informatika.svtprojekat.entity.Post;

import java.util.List;

@Repository
public interface PostRepository extends ElasticsearchRepository<Post, Integer> {

    List<Post> findAll();
    List<Post> findAllByCommunityId(String communityId);

    List<Post> findAllByUserId(Integer userId);

    void deleteById(String id);

    List<Post> findAllByText(String text);

    List<Post> findAllByTitle(String title);

    List<Post> findPostByTextOrTitle(String input, String input2);

    Post findById(String id);

    List<Post> findPostByPdfText(String text);
}
