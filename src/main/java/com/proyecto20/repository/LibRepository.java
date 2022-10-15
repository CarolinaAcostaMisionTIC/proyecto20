package com.proyecto20.repository;

import com.proyecto20.model.Lib;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibRepository extends CrudRepository<Lib,Integer> {
}
