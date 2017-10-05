package org.launchcode.models.data;

import org.launchcode.models.Category;
import org.launchcode.models.Description;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by t420-11 on 10/5/2017.
 */
@Repository
@Transactional
public interface DescriptionDao extends CrudRepository<Description, Integer> {
}
