package com.github.jenny492.kampanjaloki.repository;

import org.springframework.data.repository.CrudRepository;

import com.github.jenny492.kampanjaloki.domain.GameEvent;

public interface GameEventRepository extends CrudRepository<GameEvent, Long> {

}
