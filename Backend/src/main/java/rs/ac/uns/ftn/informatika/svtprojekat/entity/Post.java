package rs.ac.uns.ftn.informatika.svtprojekat.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;
@Data
@Entity
@Table(name = "post")
public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "post_id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "title", nullable = false)
    @Size(min = 5, max = 25, message
            = "Title must be between 5 and 25 characters")
    private String title;

    @Column(name = "text", nullable = false)
    @Size(min = 10, max = 200, message
            = "Text must be between 10 and 200 characters")
    private String text;

    @Column(name = "creation_date", nullable = false)
    private LocalDate creationDate;

    @Column(name = "image_path", nullable = false)
    @NotNull(message = "Image cannot be null")
    private String imagePath;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "flair_id", referencedColumnName = "flair_id", nullable = false)
    private Flair flair;

    @ManyToOne
    @JoinColumn(name = "community_id", nullable = false)
    private Community community;

    public Post() {
    }



}
