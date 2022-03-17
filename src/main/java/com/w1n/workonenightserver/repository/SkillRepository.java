package com.w1n.workonenightserver.repository;

import com.w1n.workonenightserver.model.Skill;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillRepository extends MongoRepository<Skill,String> {

    // QUESTA QUERY SERVE PER RENDERE LA RICERCA CASE INSENSITIVE
    @Query(value = "{'nomeskill': {$regex : ?0, $options: 'i'}}")
    List<Skill> findAllByNomeskillRegex(String regex);


    Skill findByNomeskill(String nomeskill);

}
