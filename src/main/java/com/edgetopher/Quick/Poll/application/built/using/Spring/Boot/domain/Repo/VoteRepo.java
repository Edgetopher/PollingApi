package com.edgetopher.Quick.Poll.application.built.using.Spring.Boot.domain.Repo;

import com.edgetopher.Quick.Poll.application.built.using.Spring.Boot.domain.Domain.Vote;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface VoteRepo extends CrudRepository<Vote,Long> {

    @Query(value = "select v.* from Option o, Vote v where o.POLL_ID =?1 and v.OPTION_ID = o.OPTION_ID",nativeQuery = true)
    Iterable<Vote> findByPoll(Long pollId);
}
