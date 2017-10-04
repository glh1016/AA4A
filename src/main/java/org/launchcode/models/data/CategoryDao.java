package org.launchcode.models.data;

import org.launchcode.models.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by t420-11 on 8/7/2017.
 */
@Repository
@Transactional
public interface CategoryDao extends CrudRepository<Category, Integer> {
}
