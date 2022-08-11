package com.spring.archivageapplication.Repository;

import com.spring.archivageapplication.Models.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Integer> {


}
