package rs.ac.uns.ftn.informatika.svtprojekat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.informatika.svtprojekat.entity.Community;
import rs.ac.uns.ftn.informatika.svtprojekat.entity.Moderator;

import java.util.List;

@Repository
public interface ModeratorRepository extends JpaRepository<Moderator, Integer> {

    List<Moderator> findAllByCommunity(Community community);

}
