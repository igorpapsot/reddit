package rs.ac.uns.ftn.informatika.svtprojekat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.informatika.svtprojekat.entity.Community;
import rs.ac.uns.ftn.informatika.svtprojekat.entity.Moderator;
import rs.ac.uns.ftn.informatika.svtprojekat.entity.dto.CommunityDTO;
import rs.ac.uns.ftn.informatika.svtprojekat.entity.es.CommunityTextDTO;
import rs.ac.uns.ftn.informatika.svtprojekat.service.CommunityService;
import rs.ac.uns.ftn.informatika.svtprojekat.service.ModeratorService;
import rs.ac.uns.ftn.informatika.svtprojekat.service.UserService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "communities")
public class CommunityController {

    @Autowired
    private CommunityService communityService;

    @Autowired
    private UserService userService;

    @Autowired
    private ModeratorService moderatorService;

    @GetMapping("/name/{name}")
    public ResponseEntity<List<CommunityDTO>> findCommunitiesByName(@PathVariable String name){
        List<Community> communities = communityService.findByName(name);

        List<CommunityDTO> communitiesDTO = getCommunityDTOList(communities);

        return new ResponseEntity<>(communitiesDTO, HttpStatus.OK);
    }



    @GetMapping("/description/{description}")
    public ResponseEntity<List<CommunityDTO>> findCommunitiesByDescription(@PathVariable String description){
        List<Community> communities = communityService.findByText(description);

        List<CommunityDTO> communitiesDTO = getCommunityDTOList(communities);

        return new ResponseEntity<>(communitiesDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CommunityDTO>> getCommunities() {
        List<Community> communities = communityService.findAll();

        List<CommunityDTO> communitiesDTO = getCommunityDTOList(communities);

        return new ResponseEntity<>(communitiesDTO, HttpStatus.OK);
    }

    private List<CommunityDTO> getCommunityDTOList(List<Community> communities) {
        List<CommunityDTO> communitiesDTO = new ArrayList<>();
        for (Community c : communities) {
            CommunityDTO cDTO = new CommunityDTO(c);
            communitiesDTO.add(cDTO);
        }
        return communitiesDTO;
    }

    private List<CommunityDTO> getCommunitiesWithNumOfPosts(List<Community> communities) {
        List<CommunityDTO> communitiesDTO = new ArrayList<>();
        for (Community c : communities) {
            CommunityDTO cDTO = new CommunityDTO(c);

            Integer numOfPosts = communityService.getNumOfPosts(c.getId());
            cDTO.setNumOfPosts(numOfPosts);
            communitiesDTO.add(cDTO);
        }
        return communitiesDTO;
    }

    @PreAuthorize("hasAnyRole('USER', 'ROLE_ADMIN')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<CommunityDTO> getCommunity(@PathVariable("id") String id) {
        Community community = communityService.findOne(id);
        if (community == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new CommunityDTO(community), HttpStatus.OK);
    }

    @Transactional
    @PreAuthorize("hasAnyRole('USER', 'ROLE_ADMIN')")
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping
    public ResponseEntity<CommunityDTO> postCommunity(@RequestBody CommunityDTO communityDTO){
        Community community = new Community();
        Moderator moderator = new Moderator();

        LocalDate date = LocalDate.now();

        community.setDescription(communityDTO.getDescription());
        community.setName(communityDTO.getName());
        community.setCreationDate(date.toString());
        community.setSuspended(false);
        community.setId(communityService.getId());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User u = (User) auth.getPrincipal();
        rs.ac.uns.ftn.informatika.svtprojekat.entity.User user = userService.findUserByUsername(u.getUsername());

        moderator.setCommunityId(community.getId());
        moderator.setUser(user);
        if(community.getDescription() == null || community.getName() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        communityService.save(community);
        moderatorService.save(moderator);
        return new ResponseEntity<>(new CommunityDTO(community), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('USER', 'ROLE_ADMIN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<CommunityDTO> putCommunity(@RequestBody CommunityDTO communityDTO, @PathVariable("id") String id){
        if (communityDTO.getId() != null) {
            Community community = communityService.findOne(id);

            if(community == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            community.setDescription(communityDTO.getDescription());
            community.setName(communityDTO.getName());
            community.setSuspended(communityDTO.isSuspended());
            community.setSuspendedReason(communityDTO.getSuspendedReason());

            if(community.getDescription() == null || community.getName() == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            communityService.save(community);
            return new ResponseEntity<>(new CommunityDTO(community), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteCommunity(@PathVariable("id") String id) {
        if (id != null) {
            Community community = communityService.findOne(id);

            if(community == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            communityService.remove(community.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(consumes = "text/plain", value = "/{id}/suspend")
    public ResponseEntity<CommunityDTO> suspend(@PathVariable("id") String id, @RequestBody String reason){
        Community community = communityService.findOne(id);
        community.setSuspended(true);
        community.setSuspendedReason(reason);

        List<Moderator> moderators = moderatorService.findAllBycommunity(community);
        for(Moderator m : moderators) {
            moderatorService.remove(m.getId());
        }

        if(community == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        communityService.save(community);
        return new ResponseEntity<>(new CommunityDTO(community), HttpStatus.OK);
    }
}
