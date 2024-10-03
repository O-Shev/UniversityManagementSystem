package com.leshkins.universitymanagementsystem;

import com.leshkins.universitymanagementsystem.service.CommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class UniversityManagementSystemApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(UniversityManagementSystemApplication.class, args);
    }

    @Autowired
    public UniversityManagementSystemApplication(CommandService commandService) {
        this.commandService = commandService;
    }

    private final CommandService commandService;

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter command:");
            String input = scanner.nextLine();
            if(commandService.isExit(input)) break;
            commandService.execute(input);
        }
    }
}
