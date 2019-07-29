package com.stackroute.Muzixapp.controller;

import com.stackroute.Muzixapp.domain.Track;
//import com.stackroute.Muzixapp.exceptions.TrackAlreadyExistsException;
import com.stackroute.Muzixapp.exception.UserAlreadyExistsException;
import com.stackroute.Muzixapp.service.TrackService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v2")
public class  TrackController {
    TrackService trackService;
    public TrackController(TrackService trackService)
    {
        this.trackService=trackService;
    }

    @PostMapping("track")
    public ResponseEntity<?> saveUser(@RequestBody Track track){
        ResponseEntity responseEntity;
        try{
            trackService.saveUser(track);
            responseEntity=new ResponseEntity<String>("successfully created", HttpStatus.CREATED);
        }
        catch(UserAlreadyExistsException ex){
            responseEntity=new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
    @GetMapping("track")
    public ResponseEntity<?> getTracks() {
        return new ResponseEntity<List<Track>>(trackService.getAllTracks(),HttpStatus.OK);
    }
    @GetMapping("track/{id}")
    public ResponseEntity<?> getTrackById(@PathVariable(value="id") int id){
        return new ResponseEntity<Track>(trackService.getTrackById(id),HttpStatus.OK);
    }
    @DeleteMapping("track/{id}")
    public ResponseEntity<?> deleteTrack(@PathVariable(value = "id")int id){
        trackService.deleteTrack(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("track")
    public ResponseEntity<Track> updateUser(@RequestBody Track track){
        trackService.updateTrack(track);
        return new ResponseEntity<Track>(track,HttpStatus.OK);
    }

    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<?> getTrackByName(@RequestParam(value="name") String name) {
        Track getTrack=(Track)trackService.findByName(name);
        return new ResponseEntity<Track>(getTrack,HttpStatus.OK);
    }
    @PutMapping(value = "/update")
    public ResponseEntity<?> updateTrack(@RequestBody Track track)
    {
        ResponseEntity responseEntity;
        try
        {
            trackService.saveTrack(track);
            responseEntity=new ResponseEntity<String>("Succesfully Updated",HttpStatus.CREATED);
        }
        catch(Exception e)
        {
            responseEntity=new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

}
