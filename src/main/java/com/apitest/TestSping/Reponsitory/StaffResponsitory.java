package com.apitest.TestSping.Reponsitory;

import com.apitest.TestSping.entity.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface StaffResponsitory extends JpaRepository<Staff,Integer> {

    @Query(value = "from Staff ")
    public Page<Staff> findAll(Pageable pageable);
    @Query(value = "from Staff staff where staff.nameStaff like concat ('%',?1,'%')")
    public Page<Staff> findByNameStaff(String name, Pageable pageable);
    @Query(value = "from Staff staff where staff.id =?1")
    public Optional<Staff> findByIdStaff(int id);
}
