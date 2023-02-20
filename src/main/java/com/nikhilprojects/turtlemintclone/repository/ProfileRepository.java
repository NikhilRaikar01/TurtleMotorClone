package com.nikhilprojects.turtlemintclone.repository;

import com.nikhilprojects.turtlemintclone.model.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends MongoRepository<Profile, Long>{
}
