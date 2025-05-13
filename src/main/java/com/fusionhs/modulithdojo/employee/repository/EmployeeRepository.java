package com.fusionhs.modulithdojo.employee.repository;

import com.fusionhs.modulithdojo.employee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByDepartment(Employee.Department department);
    
    @Query("""
            SELECT e FROM Employee e 
            WHERE LOWER(e.firstName) LIKE :#{#term == null ? '%' : '%' + #term + '%'} 
               OR LOWER(e.lastName) LIKE :#{#term == null ? '%' : '%' + #term + '%'}
               OR LOWER(e.email) LIKE :#{#term == null ? '%' : '%' + #term + '%'}
            """)
    List<Employee> searchByTerm(@Param("term") String term);
} 