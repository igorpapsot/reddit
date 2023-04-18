package rs.ac.uns.ftn.informatika.svtprojekat.entity.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
public class PostDtoPdf {

    private String id;

    private String title;

    private String text;

    private LocalDate creationDate;

    private String imagePath;

    private String userId;

    private FlairDTO flair;

    private String communityId;

    private String username;

    private int karma;

    private String flairId;

    private MultipartFile[] files;
}
