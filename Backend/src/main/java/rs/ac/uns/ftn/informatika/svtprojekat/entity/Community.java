package rs.ac.uns.ftn.informatika.svtprojekat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "communities")
@Setting(settingPath = "/analyzers/serbianAnalyzer.json")
public class Community implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private String id;

    @Field(type = FieldType.Text)
    private String name;

    @Field(type = FieldType.Text)
    private String description;

    @Field(type = FieldType.Date)
    private String creationDate;

    @Field(type = FieldType.Boolean)
    private boolean isSuspended;

    @Field(type = FieldType.Text)
    private String suspendedReason;

    private String keywords;

    private String filename;

    //private List<Flair> flairs;

    //private Set<Moderator> moderators = new HashSet<Moderator>();

    //private Set<Post> posts = new HashSet<Post>();


}
