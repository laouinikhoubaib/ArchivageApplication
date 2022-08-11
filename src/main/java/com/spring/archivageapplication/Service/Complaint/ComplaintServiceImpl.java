package com.spring.archivageapplication.Service.Complaint;


import com.spring.archivageapplication.Models.Complaint;
import com.spring.archivageapplication.Models.File;
import com.spring.archivageapplication.Models.User;
import com.spring.archivageapplication.Repository.ComplaintRepository;
import com.spring.archivageapplication.Repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ComplaintServiceImpl implements  ComplaintService{

    @Autowired
    ComplaintRepository Crepo;

    @Autowired
    userRepository ur;


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


    public void affectatComplaintToUser(int Complaint_id, long id) {
        User user=ur.findById(id).get();
        Complaint complaint=Crepo.findById(Complaint_id).get();
        complaint.setUser(user);
        Crepo.save(complaint);

    }

}