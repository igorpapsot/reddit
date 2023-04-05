package rs.ac.uns.ftn.informatika.svtprojekat.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.informatika.svtprojekat.entity.Flair;
import rs.ac.uns.ftn.informatika.svtprojekat.repository.FlairRepository;
import rs.ac.uns.ftn.informatika.svtprojekat.service.FlairService;

import java.util.List;

@Service
public class FlairServiceImpl implements FlairService {

    @Autowired
    FlairRepository repository;

    @Override
    public List<Flair> findAllByParent(Flair parent) {
        return null;
    }

    @Override
    public Flair findOne(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Flair> findAll() {
        return repository.findAll();
    }

    @Override
    public Flair save(Flair flair) {
        return null;
    }

    @Override
    public void remove(Integer id) {

    }
}
