package com.apitest.TestSping.SeviceImpl;

import com.apitest.TestSping.database.DTOStaff;
import com.apitest.TestSping.entity.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


public interface StaffServiceImpl {
    public Page<Staff> finByNameStaff(String name, Pageable pageable);
    public Page<Staff> findByAll(Pageable pageable);
    public Optional<Staff> findByIdStaff(int id);
    public Optional<Staff> save(Staff staff);
    public boolean dele(int id);
    public Optional<Staff> update(DTOStaff dtostaff);
}
