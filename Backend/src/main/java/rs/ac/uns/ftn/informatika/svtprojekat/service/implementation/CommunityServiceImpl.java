package rs.ac.uns.ftn.informatika.svtprojekat.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.informatika.svtprojekat.entity.Community;
import rs.ac.uns.ftn.informatika.svtprojekat.repository.CommunityRepository;
import rs.ac.uns.ftn.informatika.svtprojekat.service.CommunityService;

import java.util.List;

import static org.elasticsearch.common.UUIDs.base64UUID;

@Service
public class CommunityServiceImpl implements CommunityService {

    @Autowired
    CommunityRepository repository;

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
}
