package com.leshkins.universitymanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CommandService {
    public static final Pattern HEAD_OF_DEPARTMENT = Pattern.compile("Who is head of department (.+)");
    public static final Pattern SHOW_STATISTICS = Pattern.compile("Show (.+) statistics");
    public static final Pattern SHOW_AVERAGE_SALARY = Pattern.compile("Show the average salary for the department (.+)");
    public static final Pattern COUNT_EMPLOYEES = Pattern.compile("Show count of employee for (.+)");
    public static final Pattern GLOBAL_SEARCH = Pattern.compile("Global search by (.+)");
    public static final Pattern EXIT = Pattern.compile("q");

    private final DepartmentService departmentService;
    private final LectorService lectorService;


    @Autowired
    public CommandService(DepartmentService departmentService, LectorService lectorService) {
        this.departmentService = departmentService;
        this.lectorService = lectorService;
    }

    public boolean isExit(String command){
        return EXIT.matcher(command).matches();
    }

    public void execute(String command) {
        Matcher matcher;

        // Match command: Who is head of department {department_name}
        matcher = HEAD_OF_DEPARTMENT.matcher(command);
        if (matcher.matches()) {
            String departmentName = matcher.group(1).trim();
            departmentService.nameOfDepartmentHead(departmentName).ifPresentOrElse(
                    departmentHeadName -> System.out.println("Head of " + departmentName + " department is " + departmentHeadName),
                    () -> System.out.println("There aren't department with name " + departmentName)
            );
            return;
        }

        // Match command: Show {department_name} statistics
        matcher = SHOW_STATISTICS.matcher(command);
        if (matcher.matches()) {
            String departmentName = matcher.group(1).trim();
            departmentService.getDepartmentStatistics(departmentName).ifPresentOrElse(
                    departmentStatisticsDTO -> System.out.println(
                            "assistants - " + departmentStatisticsDTO.assistants()+ "\n" +
                            "associate professors - " + departmentStatisticsDTO.associateProfessors() + "\n" +
                            "professors - " + departmentStatisticsDTO.professors()
                    ),
                    ()-> System.out.println("There aren't department with name " + departmentName)
            );
            return;
        }

        // Match command: Show the average salary for the department {department_name}
        matcher = SHOW_AVERAGE_SALARY.matcher(command);
        if (matcher.matches()) {
            String departmentName = matcher.group(1).trim();
            departmentService.getDepartmentAverageSalary(departmentName).ifPresentOrElse(
                    averageSalary -> System.out.println("The average salary of " + departmentName + " is " + averageSalary),
                    ()-> System.out.println("There aren't department with name " + departmentName)
            );
            return;
        }

        // Match command: Show count of employee for {department_name}
        matcher = COUNT_EMPLOYEES.matcher(command);
        if (matcher.matches()) {
            String departmentName = matcher.group(1).trim();
            departmentService.getCountOfEmployee(departmentName).ifPresentOrElse(
                    System.out::println,
                    ()-> System.out.println("There aren't department with name " + departmentName)
            );
            return;
        }

        // Match command: Global search by {template}
        matcher = GLOBAL_SEARCH.matcher(command);
        if (matcher.matches()) {
            String template = matcher.group(1).trim();
            List<String> names = lectorService.globalSearchByTemplate(template);
            if(names.isEmpty()) System.out.println("Didn't find any lector with template: " + template);
            else System.out.println(String.join(", ", names));
            return;
        }

        // If no match, inform the user that the command is not recognized
        System.out.println("Unknown command. Please try again.");
    }

}
