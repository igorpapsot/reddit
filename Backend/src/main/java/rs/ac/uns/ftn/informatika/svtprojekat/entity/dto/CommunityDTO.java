package rs.ac.uns.ftn.informatika.svtprojekat.entity.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import rs.ac.uns.ftn.informatika.svtprojekat.entity.Community;
import rs.ac.uns.ftn.informatika.svtprojekat.entity.Moderator;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
public class CommunityDTO implements Serializable {

    private String id;

    private String name;

    private String description;

    private String creationDate;

    private boolean isSuspended;

    private String suspendedReason;

    private Set<String> modUsernames;

    private Integer numOfPosts;

    private Integer karma;

    private MultipartFile[] files;

    public CommunityDTO() {
    }

    public CommunityDTO(String id, String name, String description, String creationDate, boolean isSuspended, String suspendedReason) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.creationDate = creationDate;
        this.isSuspended = isSuspended;
        this.suspendedReason = suspendedReason;
        modUsernames = new HashSet<>();

    }

    public CommunityDTO(Community community) {
        this(community.getId(), community.getName(), community.getDescription(), community.getCreationDate(), community.isSuspended(), community.getSuspendedReason());
    }
}
