package rs.ac.uns.ftn.informatika.svtprojekat.entity;

import lombok.Data;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "flair")
@Data
public class Flair implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "flair_id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "community_flair",
            joinColumns = @JoinColumn(name = "flair_id", referencedColumnName = "flair_id"),
            inverseJoinColumns = @JoinColumn(name = "community_id", referencedColumnName = "community_id"))
    private Set<Community> communities = new HashSet<Community>();

    public Flair() {
    }

}
