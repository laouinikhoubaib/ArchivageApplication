package com.spring.archivageapplication.Service.Complaint;

import com.spring.archivageapplication.Models.Complaint;
import com.spring.archivageapplication.Models.ComplaintStatus;

import javax.mail.MessagingException;
import java.util.List;

public interface ComplaintService {

    public Complaint addComplaint(Complaint T);
    public void deleteComplaint(Integer id);
    public List<Complaint> retrieveAllComplaints();

    public void affectatComplaintToUser(int Complaint_id, long id);
}
