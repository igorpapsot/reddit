package rs.ac.uns.ftn.informatika.svtprojekat.entity.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ModeratorDTO implements Serializable {

    private Integer id;

    private CommunityDTO communityDTO;

    public ModeratorDTO() {
    }
}
