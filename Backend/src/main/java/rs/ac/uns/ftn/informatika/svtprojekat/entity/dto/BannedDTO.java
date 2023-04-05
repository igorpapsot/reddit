package rs.ac.uns.ftn.informatika.svtprojekat.entity.dto;

import lombok.Data;
import rs.ac.uns.ftn.informatika.svtprojekat.entity.Community;
import rs.ac.uns.ftn.informatika.svtprojekat.entity.Moderator;
import rs.ac.uns.ftn.informatika.svtprojekat.entity.User;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class BannedDTO implements Serializable {

    private Integer id;

    private LocalDate timestamp;

    private UserDTO user;

    private ModeratorDTO moderator;

    private CommunityDTO community;

    public BannedDTO() {
    }
}
