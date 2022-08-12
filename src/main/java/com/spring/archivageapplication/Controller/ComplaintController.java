package com.spring.archivageapplication.Controller;


import com.spring.archivageapplication.Models.Complaint;
import com.spring.archivageapplication.Service.Complaint.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/complaint")
public class ComplaintController {

    @Autowired
    ComplaintService ComplaintService;


    @PostMapping("/AddComplaint")
    @ResponseBody
    public Complaint addComplaint(@RequestBody Complaint T) {
        return ComplaintService.addComplaint(T);

    }

    @DeleteMapping("/deleteComplaint/{Complaint_id}")
    public void deleteComplaint(@PathVariable("Complaint_id") Integer id) {
        ComplaintService.deleteComplaint(id);

    }

    @GetMapping("/retrieveAllComplaints")
    public List<Complaint> retrieveAllComplaints() {

        return ComplaintService.retrieveAllComplaints();
    }

    @PutMapping("/updateCompalaint/{Compalaint-id}")
    public void updateComplaint(@RequestBody Complaint newcomplaint, @PathVariable("Compalaint-id") int idComplaint) {
        ComplaintService.updateComplaint(newcomplaint, idComplaint);
    }
    @PutMapping("/updateUntreatedComplaint/{Compalaint-id}")
    @Transactional
    public Complaint updateComplaint2(@PathVariable(value ="Compalaint-id")Integer id) throws MessagingException {
        return ComplaintService.updateComplaint2(id);
    }

    @PostMapping("/affectatComplaintToUser/{Complaint_id}/{id}")
    @ResponseBody
    public void affectatComplaintToUser(@PathVariable("Complaint_id") int Complaint_id,@PathVariable("id")int id) throws IOException {

        ComplaintService.affectatComplaintToUser(Complaint_id, id);
    }

}

