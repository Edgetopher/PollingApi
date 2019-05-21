package com.edgetopher.Quick.Poll.application.built.using.Spring.Boot.domain.Controller;

import com.edgetopher.Quick.Poll.application.built.using.Spring.Boot.domain.Domain.Vote;
import com.edgetopher.Quick.Poll.application.built.using.Spring.Boot.domain.Repo.VoteRepo;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.inject.Inject;

@RestController
public class VoteController {
    @Inject
    private VoteRepo voteRepo;

    @RequestMapping(value = "polls/{pollId}/votes",method = RequestMethod.POST)
    public ResponseEntity<?> createVote(@PathVariable Long pollId, @RequestBody Vote vote){
        vote = voteRepo.save(vote);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(ServletUriComponentsBuilder
                .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(vote.getId())
        .toUri());

        return new ResponseEntity<>(null,responseHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/polls/{pollId}/votes", method = RequestMethod.GET)
    public Iterable<Vote> getAllVotes(@PathVariable Long id){
        return voteRepo.findByPoll(id);
    }
}
