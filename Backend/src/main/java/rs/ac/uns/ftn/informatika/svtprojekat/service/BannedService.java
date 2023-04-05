package rs.ac.uns.ftn.informatika.svtprojekat.service;

import rs.ac.uns.ftn.informatika.svtprojekat.entity.Banned;

import java.util.List;

public interface BannedService {

    List<Banned> findAllByParent(Banned parent);

    Banned findOne(Integer id);

    List<Banned> findAll();

    Banned save(Banned banned);

    void remove(Integer id);

}
