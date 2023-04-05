package rs.ac.uns.ftn.informatika.svtprojekat.entity.dto;

import lombok.Data;
import rs.ac.uns.ftn.informatika.svtprojekat.entity.Community;
import rs.ac.uns.ftn.informatika.svtprojekat.entity.Moderator;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
public class CommunityDTO implements Serializable {

    private Integer id;

    private String name;

    private String description;

    private String creationDate;

    private boolean isSuspended;

    private String suspendedReason;

    private Set<String> modUsernames;

    public CommunityDTO() {
    }

    public CommunityDTO(Integer id, String name, String description, String creationDate, boolean isSuspended, String suspendedReason, Set<Moderator> moderatorSet) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.creationDate = creationDate;
        this.isSuspended = isSuspended;
        this.suspendedReason = suspendedReason;
        modUsernames = new HashSet<>();
        for(Moderator m : moderatorSet) {
            modUsernames.add(m.getUser().getUsername());
        }
    }

    public CommunityDTO(Community community) {
        this(community.getId(), community.getName(), community.getDescription(), community.getCreationDate(), community.isSuspended(), community.getSuspendedReason(), community.getModerators());
    }
}
