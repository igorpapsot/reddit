package rs.ac.uns.ftn.informatika.svtprojekat.entity.dto;

import lombok.Data;
import rs.ac.uns.ftn.informatika.svtprojekat.entity.Community;
import rs.ac.uns.ftn.informatika.svtprojekat.entity.Flair;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
public class FlairDTO implements Serializable {

    private Integer id;

    private String name;

    public FlairDTO() {
    }

    public FlairDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public FlairDTO(Flair flair) {
        this(flair.getId(), flair.getName());
    }
}
