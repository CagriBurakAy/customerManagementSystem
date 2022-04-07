package com.nish.project.repository.customerfiles;

import com.nish.project.model.customerFiles.CustomerFiles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerFilesRepository extends JpaRepository<CustomerFiles,Integer> {
    List<CustomerFiles> findByCustomerId(Integer id);
}
