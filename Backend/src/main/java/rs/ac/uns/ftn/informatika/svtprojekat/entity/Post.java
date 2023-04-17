package rs.ac.uns.ftn.informatika.svtprojekat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "posts")
@Setting(settingPath = "/analyzers/serbianAnalyzer.json")
public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private String id;

    @Field(type = FieldType.Text)
    private String title;

    @Field(type = FieldType.Text)
    private String text;

    @Field(type = FieldType.Date)
    private LocalDate creationDate;

    @Field(type = FieldType.Integer)
    private Integer userId;

    @Field(type = FieldType.Keyword)
    private Flair flair;

    @Field(type = FieldType.Keyword)
    private String communityId;

    private String keywords;

    private String filename;

    private MultipartFile[] files;

    @Field(type = FieldType.Text)
    private String pdfText;
}
