package com.nish.project.repository.files;

import com.nish.project.model.files.Files;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilesRepository extends JpaRepository<Files,Integer> {
}
