package com.edgetopher.Quick.Poll.application.built.using.Spring.Boot.domain.Repo;

import com.edgetopher.Quick.Poll.application.built.using.Spring.Boot.domain.Domain.Poll;
import org.springframework.data.repository.CrudRepository;

public interface PollRepo extends CrudRepository<Poll,Long> {
}
