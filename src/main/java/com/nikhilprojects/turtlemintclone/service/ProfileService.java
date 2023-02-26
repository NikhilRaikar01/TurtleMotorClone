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
import java.util.Optional;

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
    public Optional<Profile> getProfileById(Long profileId)
    {
        Optional<Profile> profile = profileRepository.findById(profileId);
        return profile;
    }

    @PostMapping("/profiles")
    public Profile createProfile(Profile profile) {
        profile.setId(sequenceGeneratorService.generateSequence(Profile.SEQUENCE_NAME));
        return profileRepository.save(profile);
    }


}
