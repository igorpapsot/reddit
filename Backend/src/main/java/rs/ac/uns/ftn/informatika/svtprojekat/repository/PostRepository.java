package rs.ac.uns.ftn.informatika.svtprojekat.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.informatika.svtprojekat.entity.Community;
import rs.ac.uns.ftn.informatika.svtprojekat.entity.Post;
import rs.ac.uns.ftn.informatika.svtprojekat.entity.User;

import java.util.List;

@Repository
public interface PostRepository extends ElasticsearchRepository<Post, Integer> {

    List<Post> findAll();
    List<Post> findAllByCommunityId(String communityId);

    List<Post> findAllByUserId(Integer userId);

    void deleteById(String id);

    List<Post> findAllByText(String text);

    List<Post> findAllByTitle(String title);
}
