package rs.ac.uns.ftn.informatika.svtprojekat.service;

import rs.ac.uns.ftn.informatika.svtprojekat.entity.Community;

import java.util.List;

public interface CommunityService {

    List<Community> findAllByParent(Community parent);

    Community findOne(Integer id);

    List<Community> findAll();

    Community save(Community community);

    void remove(Integer id);

}
