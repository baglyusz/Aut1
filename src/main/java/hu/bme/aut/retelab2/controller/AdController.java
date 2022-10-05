package hu.bme.aut.retelab2.controller;

import hu.bme.aut.retelab2.domain.Ad;
import hu.bme.aut.retelab2.repository.AdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ads")
public class AdController {

    @Autowired
    private AdRepository adRepository;

    @GetMapping
    public List<Ad> getAll(@RequestParam(required = false, defaultValue = "") String keyword) {
        return keyword.equals("") ? adRepository.findAll() : adRepository.findByKeyword(keyword);
    }

    @GetMapping("{id}")
    public ResponseEntity<Ad> getById(@PathVariable long id) {
        Ad ad = adRepository.findById(id);
        if (ad == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(ad);
    }

    @PostMapping
    public Ad create(@RequestBody Ad ad) {
        ad.setId(null);
        return adRepository.save(ad);
    }
}
