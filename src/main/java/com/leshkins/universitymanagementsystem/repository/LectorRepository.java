package com.leshkins.universitymanagementsystem.repository;

import com.leshkins.universitymanagementsystem.model.Degree;
import com.leshkins.universitymanagementsystem.model.Department;
import com.leshkins.universitymanagementsystem.model.Lector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface LectorRepository extends JpaRepository<Lector, Long> {
    List<Lector> findByNameContainingIgnoreCase(String template);

    int countByDepartmentsAndDegree(Set<Department> departments, Degree degree);

    @Query("SELECT AVG(l.salary) FROM Lector l JOIN l.departments d WHERE d.id = :departmentId")
    Optional<Double> findAverageSalaryByDepartmentId(@Param("departmentId") Long departmentId);

    Long countByDepartments(Set<Department> departments);
}
