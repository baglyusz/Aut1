package hu.bme.aut.retelab2.controller;

import hu.bme.aut.retelab2.domain.Ad;
import hu.bme.aut.retelab2.repository.AdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
        List<Ad> modified = new ArrayList<>();
        if(min == 0 && max == 10000000){
            modified = adRepository.findAll();
            for(int i =0; i < modified.size(); i++){
                modified.get(i).setToken("0");
            }
        }

        else {
            modified = adRepository.findPriceMinMax(min,max);
            for(int i =0; i < modified.size(); i++){
                modified.get(i).setToken("0");
            }
        }
        return new ResponseEntity<List<Ad>>(modified,HttpStatus.OK);
    }

    @GetMapping("{tag}")
    public ResponseEntity<List<Ad>> getByTags(@PathVariable String tag){
        List<Ad> response = adRepository.findByTag(tag);
        for(int i = 0;  i < response.size(); i++){
            response.get(i).setToken("0");
        }
        return new ResponseEntity<List<Ad>>(response,HttpStatus.OK);
    }

    @PostMapping
    public Ad create(@RequestBody Ad ad) {
        ad.setId(null);
        ad.setCreatedAt(new Date());
        return adRepository.save(ad);
    }

    @PutMapping
    public ResponseEntity<Ad> update(@RequestBody Ad ad){
        Ad newAd = new Ad();
        Ad currentAd = adRepository.findById(ad.getId());
        if(currentAd == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        if(currentAd.getToken().equals(ad.getToken())){
            newAd.setId(ad.getId());
            newAd.setAddress(ad.getAddress());
            newAd.setDescription(ad.getDescription());
            newAd.setPrice(ad.getPrice());
            newAd.setCreatedAt(currentAd.getCreatedAt());
            newAd.setToken(ad.getToken());
            newAd.setTags(ad.getTags());
            newAd.setExpirationDate(ad.getExpirationDate());
            return new ResponseEntity<>(adRepository.save(newAd),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
        }
    }

    @Scheduled(fixedDelay = 6000)
    public void removeExpiredAds(){
        List<Ad> actualAdList = adRepository.findAll();

        for(int i = 0; i < actualAdList.size(); i++ ){
            if(LocalDateTime.now().isAfter(actualAdList.get(i).getExpirationDate())){
                adRepository.deleteById(actualAdList.get(i).getId());
            }
        }
    }
}
