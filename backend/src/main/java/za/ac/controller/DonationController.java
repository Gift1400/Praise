package za.ac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.domain.Donation;
import za.ac.service.donationService.DonationServiceImpl;

import java.util.List;

@RestController
@RequestMapping("api/donations")
public class DonationController {

    public final DonationServiceImpl donationService;

    @Autowired
    public DonationController(DonationServiceImpl donationService) {
        this.donationService = donationService;
    }


    @PostMapping("/create")
    public Donation donation(@RequestBody Donation donation){
        return donationService.create(donation);
    }

    @GetMapping("/read/{donationId}")
    public Donation read(@PathVariable Integer donationId){
        return donationService.read(donationId);
    }

    @PutMapping("/update")
    public Donation update(@RequestBody Donation donation){
        return donationService.update(donation);
    }

    @DeleteMapping("/delete/{donationId}")
    public boolean delete(@PathVariable Integer donationId){
        return donationService.delete(donationId);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Donation>> getAll() {
        return ResponseEntity.ok(donationService.getAll());
    }

    @GetMapping("/getByMember/{memberId}")
    public ResponseEntity<List<Donation>> getByMember(@PathVariable String memberId) {
        return ResponseEntity.ok(donationService.getDonationsByMember(memberId));
    }

    @GetMapping("/total")
    public ResponseEntity<Double> getTotalDonations() {
        return ResponseEntity.ok(donationService.getTotalDonations());
    }

}
