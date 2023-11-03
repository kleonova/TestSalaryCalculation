package test.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import test.EmployeeGroup;
import test.model.Employee;

import java.util.Collection;
import java.util.List;

@Repository
public interface EmployeeDao extends JpaRepository<Employee, Integer> {
    List<Employee> findEmployeeByEmployeeLead(int emloyeeLead);

    @Query("FROM Employee WHERE employmentGroup in :groups")
    List<Employee> findAllByGroups(List<EmployeeGroup> groups);

}
