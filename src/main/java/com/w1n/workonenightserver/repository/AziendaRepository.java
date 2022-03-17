package com.w1n.workonenightserver.repository;

import com.w1n.workonenightserver.model.Azienda;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AziendaRepository extends MongoRepository<Azienda,String> {
}
