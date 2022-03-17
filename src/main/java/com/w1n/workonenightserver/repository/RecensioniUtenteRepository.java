package com.w1n.workonenightserver.repository;

import com.w1n.workonenightserver.model.RecensioniUtente;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecensioniUtenteRepository extends MongoRepository<RecensioniUtente,String> {
    RecensioniUtente findByUid(String uid);
}
