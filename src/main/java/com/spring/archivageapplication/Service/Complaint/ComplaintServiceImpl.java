package com.spring.archivageapplication.Service.Complaint;


import com.spring.archivageapplication.Models.Complaint;
import com.spring.archivageapplication.Models.ComplaintStatus;
import com.spring.archivageapplication.Models.User;
import com.spring.archivageapplication.Repository.ComplaintRepository;
import com.spring.archivageapplication.Repository.userRepository;
import com.spring.archivageapplication.Service.Email.ComplaintEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
public class ComplaintServiceImpl implements  ComplaintService{

    @Autowired
    ComplaintRepository Crepo;

    @Autowired
    userRepository ur;

    @Autowired
    ComplaintEmail es;


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

    @Override
    public void updateComplaint(Complaint newcomplaint, int idComplaint) {
        Complaint c = Crepo.findById(idComplaint).orElse(null);
        c.setComplaintDate(newcomplaint.getComplaintDate());
        c.setDescription(newcomplaint.getDescription());
        c.setName(newcomplaint.getName());
        Crepo.save(c);

    }

    @Override
    public Complaint updateComplaint2(Integer id) throws MessagingException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        Complaint oldComplaint = Crepo.getOne(id);

        String Email=oldComplaint.getUser().getEmail();
        String fname=oldComplaint.getUser().getFirstname();
        String lname=oldComplaint.getUser().getLastname();
        Date requestDate= (Date) oldComplaint.getComplaintDate();
        if (oldComplaint.isEtat()==false)
        {
            oldComplaint.setComplaint_status(ComplaintStatus.treated);
            oldComplaint.setEtat(true);
            es.sendSimpleMessage(Email, "Check in your Complaint requested on "+requestDate+""+ "with id" +oldComplaint.getComplaint_id(),
                    "Hello Mr/Mrs" + "" +fname+ "" +lname+"your complaint is treated succefully on"+""+now,"/Users/khoubaib/Desktop/khoubaib.jpg");
        }

        return Crepo.save(oldComplaint);
    }
    public void affectatComplaintToUser(int Complaint_id, long id) {
        User user=ur.findById(id).get();
        Complaint complaint=Crepo.findById(Complaint_id).get();
        complaint.setUser(user);
        Crepo.save(complaint);

    }

}