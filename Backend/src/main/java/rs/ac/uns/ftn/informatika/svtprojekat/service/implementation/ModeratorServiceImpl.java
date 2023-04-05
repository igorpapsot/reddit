package rs.ac.uns.ftn.informatika.svtprojekat.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.informatika.svtprojekat.entity.Community;
import rs.ac.uns.ftn.informatika.svtprojekat.entity.Moderator;
import rs.ac.uns.ftn.informatika.svtprojekat.repository.ModeratorRepository;
import rs.ac.uns.ftn.informatika.svtprojekat.service.ModeratorService;

import java.util.List;

@Service
public class ModeratorServiceImpl implements ModeratorService {

    @Autowired
    ModeratorRepository repository;

    @Override
    public List<Moderator> findAllByParent(Moderator parent) {
        return null;
    }

    @Override
    public Moderator findOne(Integer id) {
        return null;
    }

    @Override
    public List<Moderator> findAll() {
        return null;
    }

    @Override
    public Moderator save(Moderator moderator) {
        repository.save(moderator);
        return moderator;
    }

    @Override
    public void remove(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public List<Moderator> findAllBycommunity(Community community) {
        return repository.findAllByCommunity(community);
    }
}
