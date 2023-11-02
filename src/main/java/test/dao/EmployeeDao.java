package test.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.model.Employee;

@Repository
public interface EmployeeDao extends JpaRepository<Employee, Integer> {
}
