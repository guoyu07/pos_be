package com.ddabadi.pos.service.impl;

import com.ddabadi.pos.domain.CategoryBarang;
import com.ddabadi.pos.domain.repository.CategoryBarangRepository;
import com.ddabadi.pos.service.GenericService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: deddy
 * Date: 11/20/17
 * Time: 10:39 PM
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional(Transactional.TxType.REQUIRED)
public class CategoryBarangServiceImpl implements GenericService<CategoryBarang> {

    private final Logger logger = Logger.getLogger(CategoryBarangServiceImpl.class);

    @Autowired
    private CategoryBarangRepository repository;

    @Override
    public CategoryBarang save(CategoryBarang categoryBarang) {
        logger.info("Save");
        if(categoryBarang.getId()==0){
            return repository.save(categoryBarang);
        }else{
            CategoryBarang checkData = this.getById(categoryBarang.getId());
            if(checkData == null){
                return repository.save(categoryBarang);
            }   else{
                return this.update(categoryBarang);
            }
        }


    }

    @Override
    public CategoryBarang update(CategoryBarang categoryBarang) {
        logger.info("Update");
        CategoryBarang categoryBarangUpdate = repository.findOne(categoryBarang.getId());
        categoryBarangUpdate.setKeterangan(categoryBarang.getKeterangan());
        categoryBarangUpdate.setLastUpdate(new Date());
        return repository.save(categoryBarangUpdate);
    }

    @Override
    public Page<CategoryBarang> getAll(PageRequest page) {
        return repository.findAll(page);  ///To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public CategoryBarang getById(Long id) {
        return repository.findOne(id);  //To change body of implemented methods use File | Settings | File Templates.
    }
}
