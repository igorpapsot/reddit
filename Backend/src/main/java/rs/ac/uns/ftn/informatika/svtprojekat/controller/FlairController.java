package rs.ac.uns.ftn.informatika.svtprojekat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.uns.ftn.informatika.svtprojekat.entity.Flair;
import rs.ac.uns.ftn.informatika.svtprojekat.entity.dto.FlairDTO;
import rs.ac.uns.ftn.informatika.svtprojekat.service.FlairService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "flairs")
public class FlairController {

    @Autowired
    private FlairService flairService;

    @GetMapping
    public ResponseEntity<List<FlairDTO>> getFlairs() {
        List<Flair> flairs = flairService.findAll();

        List<FlairDTO> flairsDTO = new ArrayList<>();
        for (Flair f : flairs) {
            System.out.println(f.toString());
            flairsDTO.add(new FlairDTO(f));
        }

        return new ResponseEntity<>(flairsDTO, HttpStatus.OK);
    }
}
