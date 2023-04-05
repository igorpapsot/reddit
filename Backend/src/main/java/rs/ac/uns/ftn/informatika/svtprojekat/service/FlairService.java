package rs.ac.uns.ftn.informatika.svtprojekat.service;

import rs.ac.uns.ftn.informatika.svtprojekat.entity.Flair;

import java.util.List;

public interface FlairService {

    List<Flair> findAllByParent(Flair parent);

    Flair findOne(Integer id);

    List<Flair> findAll();

    Flair save(Flair flair);

    void remove(Integer id);

}
