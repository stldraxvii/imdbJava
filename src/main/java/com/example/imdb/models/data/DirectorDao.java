package com.example.imdb.models.data;

import com.example.imdb.models.Director;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Matt on 6/22/2017.
 */
@Repository
@Transactional
public interface DirectorDao extends CrudRepository<Director, Integer>{
    public List<Director> findAllByOrderByNameAsc();
}
