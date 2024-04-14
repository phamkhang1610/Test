package com.apitest.TestSping.Cotroller;


import com.apitest.TestSping.Reponsitory.StaffResponsitory;
import com.apitest.TestSping.Service.StaffService;
import com.apitest.TestSping.database.DTOStaff;
import com.apitest.TestSping.entity.ResponseObject;
import com.apitest.TestSping.entity.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/staff")
public class StaffController {
    @Autowired
    private StaffService staffService;
    private StaffResponsitory res;

    @GetMapping("")
    public ResponseEntity<?> findAll(Pageable pageable){
        Page<Staff> staff = staffService.findByAll(pageable);
        return staff.isEmpty() ?
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("false", "List empty " , staff)):
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "successfull request", staff));

    }

    @GetMapping("/find-byname/{name}")
    public ResponseEntity<?> findByName(@PathVariable(name = "name") String name, Pageable pageable){
        Page<Staff> staff = staffService.finByNameStaff(name,pageable);
        return staff.isEmpty() ?
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("false", "Can not find name: " + name, staff)):
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "successfull request", staff));

    }
    @GetMapping("/find-byid/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") int id, Pageable pageable) {
        Optional<Staff> staff = staffService.findByIdStaff(id);
        return staff.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "successfull request", staff)):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("false", "Can not find id: " + id, staff));
    }
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Staff staff){
        return staffService.save(staff).isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "successfull request", staff)):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("false", "Add fail ", staff));
    }
    @DeleteMapping("/dele/{id}")
    public ResponseEntity<ResponseObject> delete(@PathVariable int id){
        return staffService.dele(id) ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "successfull delete id:"+ id, null)):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("false", "Not find id: "+id, null));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseObject> update(@RequestBody DTOStaff dtoStaff, @PathVariable int id){
        if(!staffService.findByIdStaff(id).isPresent()){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("false", "not found id: "+id,dtoStaff));
        }
        else {
            dtoStaff.setId(id);
            return staffService.update(dtoStaff).isPresent() ?
                    ResponseEntity.status(HttpStatus.OK).body(
                            new ResponseObject("ok", "successfull update :", dtoStaff)):
                    ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                            new ResponseObject("false", "Not find id: "+id, dtoStaff));
        }
    }

 }
