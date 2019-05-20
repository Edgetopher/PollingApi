package com.edgetopher.Quick.Poll.application.built.using.Spring.Boot.domain.Controller;

import com.edgetopher.Quick.Poll.application.built.using.Spring.Boot.domain.Domain.Poll;
import com.edgetopher.Quick.Poll.application.built.using.Spring.Boot.domain.Exceptions.ResourceNotFoundException;
import com.edgetopher.Quick.Poll.application.built.using.Spring.Boot.domain.Repo.PollRepo;
import javassist.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URI;
import java.util.MissingResourceException;
import java.util.Optional;

@RestController
public class PollController {

    @Inject
    private PollRepo pollRepo;


    @RequestMapping(value = "/polls",method = RequestMethod.GET)
    public ResponseEntity<Iterable<Poll>> getAllPolls(){
        Iterable<Poll> allPolls = pollRepo.findAll();
        return new ResponseEntity<>(pollRepo.findAll(), HttpStatus.OK);
    }
    protected void verifyPoll(Long pollId) throws ResourceNotFoundException{
        Poll poll = pollRepo.findById(pollId).orElse(null);
        if(poll == null){
            throw new ResourceNotFoundException("Poll with id " + pollId + "not found");
        }
    }

    @RequestMapping(value = "/polls",method = RequestMethod.POST)
    public ResponseEntity<?> createPoll(@Valid @RequestBody Poll poll) {
        poll = pollRepo.save(poll);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newPollURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(poll.getId())
                .toUri();
        responseHeaders.setLocation(newPollURI);
        return new ResponseEntity<>(null,responseHeaders,HttpStatus.CREATED);
    }
    @RequestMapping(value = "/polls/{pollId}", method = RequestMethod.GET)
    public ResponseEntity<?> getPoll(@PathVariable Long pollId){
        verifyPoll(pollId);
        Poll p = pollRepo.findById(pollId).orElse(null);
        return new ResponseEntity<>(p, HttpStatus.OK);
    }
    @RequestMapping(value = "/polls/{pollId}", method = RequestMethod.PUT)
    public ResponseEntity<?> updatePoll(@RequestBody Poll poll, @PathVariable Long pollId){
        verifyPoll(pollId);
//        for (int i = 0; i<pollRepo.count(); i++) {
//            if(pollRepo.findById(poll.getId()).equals(pollRepo.))
//        }
        Poll p = pollRepo.save(poll);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/polls/{pollId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePoll(@PathVariable Long pollId){
        verifyPoll(pollId);
        pollRepo.deleteById(pollId);
        return new ResponseEntity<>(HttpStatus.OK);

    }

}
