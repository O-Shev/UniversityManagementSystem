package com.leshkins.universitymanagementsystem.repository;

import com.leshkins.universitymanagementsystem.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Optional<Department> findByName(String name);
}
