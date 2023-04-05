package rs.ac.uns.ftn.informatika.svtprojekat.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "community")
public class Community implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "community_id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    @Size(min = 5, max = 25, message
            = "Name must be between 5 and 25 characters")
    private String name;

    @Column(name = "description", nullable = false)
    @Size(min = 10, max = 200, message
            = "Description must be between 10 and 200 characters")
    private String description;

    @Column(name = "creation_date", nullable = false)
    private String creationDate;

    @Column(name = "community_is_suspended", nullable = false)
    private boolean isSuspended;

    @Column(name = "suspended_reason")
    private String suspendedReason;

    @ManyToMany(mappedBy="communities")
    private List<Flair> flairs;

    @OneToMany(cascade = {ALL}, fetch = LAZY, mappedBy = "community")
    private Set<Moderator> moderators = new HashSet<Moderator>();

    @OneToMany(cascade = {ALL}, fetch = LAZY, mappedBy = "community")
    private Set<Post> posts = new HashSet<Post>();

    @OneToMany(cascade = {ALL}, fetch = LAZY, mappedBy = "community")
    private Set<Rule> rules = new HashSet<>();

    public Community() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Community)) return false;
        Community community = (Community) o;
        return isSuspended() == community.isSuspended() && Objects.equals(getId(), community.getId()) && Objects.equals(getName(), community.getName()) && Objects.equals(getDescription(), community.getDescription()) && Objects.equals(getCreationDate(), community.getCreationDate()) && Objects.equals(getSuspendedReason(), community.getSuspendedReason()) && Objects.equals(getFlairs(), community.getFlairs()) && Objects.equals(getRules(), community.getRules());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription(), getCreationDate(), isSuspended(), getSuspendedReason(), getFlairs(), getRules());
    }

    @Override
    public String toString() {
        return "Community{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", creationDate='" + creationDate + '\'' +
                ", isSuspended=" + isSuspended +
                ", suspendedReason='" + suspendedReason + '\'' +
                '}';
    }

    //    public void add(Post post) {
//        if (post.getCommunity() != null)
//            post.getCommunity().getPosts().remove(post);
//        post.setCommunity(this);
//        getPosts().add(post);
//    }
//
//    public void add(Rule rule) {
//        if (rule.getCommunity() != null)
//            rule.getCommunity().getRules().remove(rule);
//        rule.setCommunity(this);
//        getRules().add(rule);
//    }

}
