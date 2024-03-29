package rs.ac.uns.ftn.informatika.svtprojekat.entity;

import lombok.Data;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "banned")
@Data
public class Banned implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "banned_id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "timestamp", nullable = false)
    private LocalDate timestamp;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "moderator_id", referencedColumnName = "moderator_id", nullable = false)
    private Moderator moderator;

    @Column(name = "community_id", nullable = false, unique = true)
    private String communityId;

    public Banned() {
    }

}
