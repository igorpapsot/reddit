package rs.ac.uns.ftn.informatika.svtprojekat.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.informatika.svtprojekat.entity.Banned;
import rs.ac.uns.ftn.informatika.svtprojekat.repository.BannedRepository;
import rs.ac.uns.ftn.informatika.svtprojekat.service.BannedService;

import java.util.List;

@Service
public class BannedServiceImpl implements BannedService {

    @Autowired
    BannedRepository repository;

    @Override
    public List<Banned> findAllByParent(Banned parent) {
        return null;
    }

    @Override
    public Banned findOne(Integer id) {
        return null;
    }

    @Override
    public List<Banned> findAll() {
        return null;
    }

    @Override
    public Banned save(Banned banned) {
        return null;
    }

    @Override
    public void remove(Integer id) {

    }
}
