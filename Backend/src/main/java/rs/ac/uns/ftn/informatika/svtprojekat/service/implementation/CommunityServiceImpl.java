package rs.ac.uns.ftn.informatika.svtprojekat.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.informatika.svtprojekat.entity.Community;
import rs.ac.uns.ftn.informatika.svtprojekat.repository.CommunityRepository;
import rs.ac.uns.ftn.informatika.svtprojekat.service.CommunityService;

import java.util.List;

@Service
public class CommunityServiceImpl implements CommunityService {

    @Autowired
    CommunityRepository repository;

    @Override
    public List<Community> findAllByParent(Community parent) {
        return null;
    }

    @Override
    public Community findOne(Integer id) {
        return repository.findById(id).orElse(null);
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
    public void remove(Integer id) {
        repository.deleteById(id);
    }
}
