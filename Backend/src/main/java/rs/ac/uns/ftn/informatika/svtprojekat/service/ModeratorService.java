package rs.ac.uns.ftn.informatika.svtprojekat.service;

import rs.ac.uns.ftn.informatika.svtprojekat.entity.Community;
import rs.ac.uns.ftn.informatika.svtprojekat.entity.Moderator;

import java.util.List;

public interface ModeratorService {

    List<Moderator> findAllByParent(Moderator parent);

    Moderator findOne(Integer id);

    List<Moderator> findAll();

    Moderator save(Moderator moderator);

    void remove(Integer id);

    List<Moderator> findAllBycommunity(Community community);

}
