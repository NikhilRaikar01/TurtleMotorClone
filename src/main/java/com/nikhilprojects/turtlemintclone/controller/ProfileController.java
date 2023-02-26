package com.nikhilprojects.turtlemintclone.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.nikhilprojects.turtlemintclone.exception.ResourceNotFoundException;
import com.nikhilprojects.turtlemintclone.model.Profile;
import com.nikhilprojects.turtlemintclone.repository.ProfileRepository;
import com.nikhilprojects.turtlemintclone.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nikhilprojects.turtlemintclone.exception.ResourceNotFoundException;
import com.nikhilprojects.turtlemintclone.model.Profile;
import com.nikhilprojects.turtlemintclone.repository.ProfileRepository;
import com.nikhilprojects.turtlemintclone.service.SequenceGeneratorService;


@RestController
@RequestMapping("/turtlemint")
public class ProfileController {
    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    private ProfileService profileService;

    @GetMapping("/profiles")
    public List<Profile> getAllProfiles() {return profileService.getAllProfiles();}

    @GetMapping("/profiles/{id}")
    public ResponseEntity<Profile> getProfileById(@PathVariable(value = "id") Long profileId)
            throws ResourceNotFoundException {
        return profileService.getProfileById(profileId);
    }

    @PostMapping("/profiles")
    public Profile createProfile(@RequestBody Profile profile) {

        profile.setId(sequenceGeneratorService.generateSequence(Profile.SEQUENCE_NAME));
        return profileRepository.save(profile);
    }

    @PutMapping("/profiles/{id}")
    public ResponseEntity<Profile> updateProfile(@PathVariable(value = "id") Long profileId,
                                                   @Valid @RequestBody Profile profileDetails) throws ResourceNotFoundException {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found for this id :: " + profileId));

        profile.setModel(profileDetails.getModel());
        profile.setMake(profileDetails.getMake());
        profile.setVertical(profileDetails.getVertical());
        final Profile updatedProfile = profileRepository.save(profile);
        return ResponseEntity.ok(updatedProfile);
    }

    @DeleteMapping("/profiles/{id}")
    public Map<String, Boolean> deleteProfile(@PathVariable(value = "id") Long profileId)
            throws ResourceNotFoundException {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found for this id :: " + profileId));

        profileRepository.delete(profile);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}

