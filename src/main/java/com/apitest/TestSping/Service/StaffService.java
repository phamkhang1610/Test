package com.apitest.TestSping.Service;

import com.apitest.TestSping.Reponsitory.StaffResponsitory;
import com.apitest.TestSping.SeviceImpl.StaffServiceImpl;
import com.apitest.TestSping.database.DTOStaff;
import com.apitest.TestSping.entity.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StaffService implements StaffServiceImpl {

    @Autowired
    private StaffResponsitory staffResponsitory;
    @Override
    public Page<Staff> finByNameStaff(String name, Pageable pageable) {
        try {
            return staffResponsitory.findByNameStaff(name,pageable);
        }catch (Exception e){
            e.printStackTrace();
            return Page.empty();
        }
    }

    @Override
    public Page<Staff> findByAll(Pageable pageable) {
        try{
            return staffResponsitory.findAll(pageable);
        }catch (Exception e){
            e.printStackTrace();
            return Page.empty();
        }
    }

    @Override
    public Optional<Staff> findByIdStaff(int id) {
        try{
            Optional<Staff> staffOp = staffResponsitory.findById(id);
            return staffOp;
        }catch (Exception e){
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public Optional<Staff> save(Staff staff) {
        try{
            if(staffResponsitory.findByIdStaff(staff.getId()).isPresent()){
                return Optional.empty();
            } //Optional.ofNullable là để chuyển về Optional<staff>
            return Optional.ofNullable(staffResponsitory.save(staff));
        }catch (Exception e){
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public boolean dele(int id) {
        boolean exit = staffResponsitory.existsById(id);
        if(exit){
            staffResponsitory.deleteById(id);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Optional<Staff> update(DTOStaff dtostaff) {
        try{
            Optional<Staff> foundStaffOptional = staffResponsitory.findByIdStaff(dtostaff.getId());
            if (foundStaffOptional.isPresent()) {
                Staff foundStaff = foundStaffOptional.get();
                foundStaff.setNameStaff(dtostaff.getNameStaff());
                foundStaff.setCmnd(dtostaff.getCmnd());
                foundStaff.setAddress(dtostaff.getAddress());
                foundStaff.setBirth(dtostaff.getBirth());
                foundStaff.setImage(dtostaff.getImage());
                foundStaff.setPhone(dtostaff.getPhone());
                return Optional.ofNullable(staffResponsitory.save(foundStaff));
            } else {
                return Optional.empty();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
