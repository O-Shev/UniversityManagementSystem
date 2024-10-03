package com.leshkins.universitymanagementsystem.service;

import com.leshkins.universitymanagementsystem.dto.DepartmentStatisticsDTO;
import com.leshkins.universitymanagementsystem.model.Degree;
import com.leshkins.universitymanagementsystem.model.Department;
import com.leshkins.universitymanagementsystem.repository.DepartmentRepository;
import com.leshkins.universitymanagementsystem.repository.LectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final LectorRepository lectorRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository, LectorRepository lectorRepository) {
        this.departmentRepository = departmentRepository;
        this.lectorRepository = lectorRepository;
    }

    public Optional<String> nameOfDepartmentHead(String departmentName) {
        return departmentRepository.findByName(departmentName).map(department -> department.getHead().getName());
    }

    public Optional<DepartmentStatisticsDTO> getDepartmentStatistics(String departmentName) {
        Optional<Department> departmentOptional = departmentRepository.findByName(departmentName);

        if (departmentOptional.isEmpty()) {
            return Optional.empty();
        }

        Department department = departmentOptional.get();

        // Query the database to count the lectors by degree within the department
        int assistants = lectorRepository.countByDepartmentsAndDegree(Set.of(department), Degree.ASSISTANT);
        int associateProfessors = lectorRepository.countByDepartmentsAndDegree(Set.of(department), Degree.ASSOCIATE_PROFESSOR);
        int professors = lectorRepository.countByDepartmentsAndDegree(Set.of(department), Degree.PROFESSOR);

        // Create and return DepartmentStatisticsDTO
        DepartmentStatisticsDTO statisticsDTO = new DepartmentStatisticsDTO(assistants, associateProfessors, professors);
        return Optional.of(statisticsDTO);
    }

    public Optional<Double> getDepartmentAverageSalary(String departmentName){
        Optional<Department> departmentOptional = departmentRepository.findByName(departmentName);
        if(departmentOptional.isEmpty()) return Optional.empty();
        return lectorRepository.findAverageSalaryByDepartmentId(departmentOptional.get().getId());
    }

    public Optional<Long> getCountOfEmployee(String departmentName){
        Optional<Department> departmentOptional = departmentRepository.findByName(departmentName);
        return departmentOptional.map(department -> lectorRepository.countByDepartments(Set.of(department)));
    }

}