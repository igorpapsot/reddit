package rs.ac.uns.ftn.informatika.svtprojekat.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.informatika.svtprojekat.entity.Community;

import java.util.List;

@Repository
public interface CommunityRepository extends ElasticsearchRepository<Community, Integer> {

    List<Community> findAll();

    void deleteById(String integer);

    Community findById(String id);

    List<Community> findAllByDescription(String description);

    List<Community> findAllByName(String name);

    List<Community> findCommunityByDescriptionOrName(String input, String input2);

    List<Community> findCommunityByPdfText(String text);

}
