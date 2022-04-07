package com.nish.project.service;

import com.nish.project.model.customerFiles.CustomerFiles;
import com.nish.project.repository.customerfiles.CustomerFilesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerFilesService {

    @Autowired
    CustomerFilesRepository customerFilesRepository;

    public List<CustomerFiles> listAll(){
        return customerFilesRepository.findAll();
    }
    public void save(CustomerFiles infoHolder){
        customerFilesRepository.save(infoHolder);
    }
    public List<CustomerFiles> get(Integer id){
        return customerFilesRepository.findByCustomerId(id);
    }

    public void delete(Integer id){
        customerFilesRepository.deleteById(id);
    }
    public void update(CustomerFiles infoHolder,Integer id){
        //musteriRepository.deleteById(id);
        customerFilesRepository.save(infoHolder);
    }

}
