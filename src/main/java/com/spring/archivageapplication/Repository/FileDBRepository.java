package com.spring.archivageapplication.Repository;

import com.spring.archivageapplication.Models.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;



@Repository
public interface FileDBRepository extends JpaRepository<File, Integer> {

    @Query("select i from File i where i.uploadDate>=:start and i.uploadDate<=:end")
    public List<File> findBetweenDate(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
}
