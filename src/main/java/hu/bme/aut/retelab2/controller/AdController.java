package hu.bme.aut.retelab2.controller;

import hu.bme.aut.retelab2.domain.Ad;
import hu.bme.aut.retelab2.repository.AdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/ads")
public class AdController {

    @Autowired
    private AdRepository adRepository;

    @GetMapping
    public ResponseEntity<List<Ad>> getAll(@RequestParam(required = false,defaultValue = "0") int min, @RequestParam(required = false,defaultValue = "10000000")int max){
        List<Ad> modifiedResponse = new ArrayList<>();
        return new ResponseEntity<List<Ad>>(modifiedResponse, HttpStatus.OK);
    }

    @PostMapping
    public Ad create(@RequestBody Ad ad) {
        ad.setId(null);
        ad.setCreatedAt(new Date());
        return adRepository.save(ad);
    }
}
