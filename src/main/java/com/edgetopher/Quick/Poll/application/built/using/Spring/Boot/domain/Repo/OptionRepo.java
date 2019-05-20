package com.edgetopher.Quick.Poll.application.built.using.Spring.Boot.domain.Repo;

import com.edgetopher.Quick.Poll.application.built.using.Spring.Boot.domain.Domain.Option;
import org.springframework.data.repository.CrudRepository;

public interface OptionRepo extends CrudRepository<Option,Long> {
}
