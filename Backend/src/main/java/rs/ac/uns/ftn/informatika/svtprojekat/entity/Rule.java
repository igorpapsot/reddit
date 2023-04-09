package rs.ac.uns.ftn.informatika.svtprojekat.entity;

import lombok.Data;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "rule")
@Data
public class Rule {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "rule_id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "community_id", unique = true, nullable = false)
    private String communityId;

    public Rule(){

    }
}
