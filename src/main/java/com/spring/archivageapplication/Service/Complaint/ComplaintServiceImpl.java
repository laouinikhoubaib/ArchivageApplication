package com.spring.archivageapplication.Service.Complaint;


import com.spring.archivageapplication.Models.Complaint;
import com.spring.archivageapplication.Repository.ComplaintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ComplaintServiceImpl implements  ComplaintService{

    @Autowired
    ComplaintRepository Crepo;


    @Override
    public Complaint addComplaint(Complaint T) {
        Complaint complaint = Crepo.save(T);

        return complaint;
    }

    @Override
    public void deleteComplaint(Integer id) {
        Crepo.deleteById(id);
    }


    @Override
    public List<Complaint> retrieveAllComplaints() {

        return  (List<Complaint>) Crepo.findAll();
    }




}