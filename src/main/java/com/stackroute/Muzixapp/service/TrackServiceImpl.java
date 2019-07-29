package com.stackroute.Muzixapp.service;

import com.stackroute.Muzixapp.domain.Track;
import com.stackroute.Muzixapp.exception.UserAlreadyExistsException;
import com.stackroute.Muzixapp.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TrackServiceImpl implements TrackService{

    TrackRepository trackRepository;
    @Autowired
    public TrackServiceImpl(TrackRepository trackRepository)
    {
        this.trackRepository=trackRepository;
    }
    @Override
    public Track saveTrack(Track track)  {
        Track savedTrack = trackRepository.save(track);
        return savedTrack;
    }

    @Override
    public List<Track> getAllTracks()
    {

        return trackRepository.findAll();
    }

    @Override
    public Track saveUser(Track track) throws UserAlreadyExistsException
    {

        Track savedTrack = trackRepository.save(track);
        if (trackRepository.existsById(track.getTrackId()))
        {
            throw new UserAlreadyExistsException("Track already exists");
        }

        if(savedTrack ==null)
        {
            throw new UserAlreadyExistsException("Track already exists");
        }
        return savedTrack;
    }

    @Override
    public Track getTrackById(int id)
    {
        return trackRepository.findById(id).get();
    }
    @Override
    public void deleteTrack(int id){
        trackRepository.deleteById(id);
    }
    @Override
    public Track updateTrack(Track track){
        return trackRepository.save(track);
    }

    @Override
    public List<Track> findByName(String name) {
        return trackRepository.findByName(name);
    }
}
