package com.stackroute.Muzixapp.repository;

import com.stackroute.Muzixapp.domain.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackRepository extends JpaRepository<Track,Integer>
{


    @Query("select t from Track t where t.trackName=?1")
     public List<Track> findByName(String name);
}
