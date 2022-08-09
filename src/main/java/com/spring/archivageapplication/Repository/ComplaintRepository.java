package com.spring.archivageapplication.Repository;

import com.spring.archivageapplication.Models.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface ComplaintRepository extends JpaRepository<Complaint, Integer> {


}
