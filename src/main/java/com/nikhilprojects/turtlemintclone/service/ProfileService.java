package com.nikhilprojects.turtlemintclone.service;

import com.nikhilprojects.turtlemintclone.exception.ResourceNotFoundException;
import com.nikhilprojects.turtlemintclone.model.Profile;
import com.nikhilprojects.turtlemintclone.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @GetMapping("/profiles")
    public List<Profile> getAllProfiles() {
        return profileRepository.findAll();}

    @GetMapping("/profiles/{id}")
    public ResponseEntity<Profile> getProfileById(Long profileId)
            throws ResourceNotFoundException {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found for this id :: " + profileId));
        return ResponseEntity.ok().body(profile);
    }

//    @PostMapping("/profiles")
//    public Profile createProfile(@RequestBody Profile profile) {
//        profile.setId(sequenceGeneratorService.generateSequence(Profile.SEQUENCE_NAME));
//        return profileRepository.save(profile);
//    }

}
