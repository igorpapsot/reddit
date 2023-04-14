package rs.ac.uns.ftn.informatika.svtprojekat.service;

import rs.ac.uns.ftn.informatika.svtprojekat.entity.Community;

import java.util.List;

public interface CommunityService {

    List<Community> findAllByParent(Community parent);

    Community findOne(String id);

    List<Community> findAll();

    Community save(Community community);

    void remove(String id);

    String getId();

    List<Community> findByText(String text);

    List<Community> findByName(String name);


    Integer getNumOfPosts(String communityId);
}
