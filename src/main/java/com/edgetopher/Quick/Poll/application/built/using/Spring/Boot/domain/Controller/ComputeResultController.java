package com.edgetopher.Quick.Poll.application.built.using.Spring.Boot.domain.Controller;

import com.edgetopher.Quick.Poll.application.built.using.Spring.Boot.domain.DTO.OptionCount;
import com.edgetopher.Quick.Poll.application.built.using.Spring.Boot.domain.DTO.VoteResult;
import com.edgetopher.Quick.Poll.application.built.using.Spring.Boot.domain.Domain.Vote;
import com.edgetopher.Quick.Poll.application.built.using.Spring.Boot.domain.Repo.VoteRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ComputeResultController {

    @Inject
    private VoteRepo voteRepo;

    @RequestMapping(value = "/computeresult", method = RequestMethod.GET)
    public ResponseEntity<?> computeResult(@RequestParam Long pollId){
        VoteResult voteResult = new VoteResult();
        Iterable<Vote> allVotes = voteRepo.findByPoll(pollId);
        int totalVotes = 0;
        Map<Long, OptionCount> tempMap = new HashMap<>();
        for(Vote v : allVotes) {
            totalVotes++;
            OptionCount optionCount = tempMap.get(v.getOption().getId());
            if(optionCount == null){
                optionCount = new OptionCount();
                optionCount.setOptionId(v.getOption().getId());
                tempMap.put(v.getOption().getId(), optionCount);
            }
            optionCount.setCount(optionCount.getCount() + 1);
        }
        voteResult.setTotalVotes(totalVotes);
        voteResult.setResults(tempMap.values());
        return new ResponseEntity<>(voteResult, HttpStatus.OK);
    }

}
