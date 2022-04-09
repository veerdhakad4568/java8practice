package com.java8.practice;

import com.java8.practice.model.Employee;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;
import java.util.stream.Collectors;

@SpringBootApplication
public class Java8Application {
    public static List<Employee> getEmployee() {
        List<Employee> employeeList =
                Arrays.asList(
                        Employee.builder()
                                .firstName("Suresh")
                                .lastName("Dhakad")
                                .salary(10000.0)
                                .projects(Arrays.asList("test1", "test2"))
                                .build(),
                        Employee.builder()
                                .firstName("Veer")
                                .lastName("Dhakad")
                                .salary(15000.0)
                                .projects(Arrays.asList("test1", "test3"))
                                .build(),
                        Employee.builder()
                                .firstName("Naman")
                                .lastName("Gupta")
                                .salary(20000.0)
                                .projects(Arrays.asList("test2", "test4"))
                                .build(),
                        Employee.builder()
                                .firstName("NL")
                                .lastName("Porwal")
                                .salary(25000.0)
                                .projects(Arrays.asList("test1", "test5"))
                                .build(),
                        Employee.builder()
                                .firstName("Rohit")
                                .lastName("Gupta")
                                .salary(30000.0)
                                .projects(Arrays.asList("test2", "test3"))
                                .build());
        return employeeList;
    }

    // Example for-each
    public static void printEmployeeData(List<Employee> employeeList) {
        employeeList
                .stream()
                .forEach(employee -> System.out.println(employee));
    }

    // Example Map
    // increase employee salary by X%
    public static void increaseSalary(List<Employee> employeeList, Double percentage) {
        Double increaseByPerc = (percentage / 100) + 1;
        List<Employee> increasedEmployeeList = employeeList
                .stream()
                .map(
                        employee ->
                                Employee.builder()
                                        .firstName(employee.getFirstName())
                                        .lastName(employee.getLastName())
                                        .salary(employee.getSalary() * increaseByPerc)
                                        .projects(employee.getProjects())
                                        .build())
                .collect(Collectors.toList());
        System.out.println("Increased salary by " + percentage);
        printEmployeeData(increasedEmployeeList);
    }

    // Filter Example
    // Filter Employee whose salary is grater than X
    public static void filterEmployeeBySalaryGreaterThanX(
            List<Employee> employeeList, Double salary) {

        List<Employee> increasedEmployeeList = employeeList
                .stream()
                .filter(employee -> employee.getSalary() > salary)
                .collect(Collectors.toList());
        System.out.println("Employee whose salary is greater than " + salary);
        printEmployeeData(increasedEmployeeList);
    }

    // Filter Map Example
    // Filter Employee whose salary is grater than X then increase by Y percentage
    public static void increaseEmployeeSalary(
            List<Employee> employeeList, Double salary, Double percentage) {
        Double increaseByPerc = (percentage / 100) + 1;
        List<Employee> increasedEmployeeList = employeeList
                .stream()
                .filter(employee -> employee.getSalary() > salary)
                .map(
                        employee -> {
                          return Employee.builder()
                                  .firstName(employee.getFirstName())
                                  .lastName(employee.getLastName())
                                  .salary(employee.getSalary() * increaseByPerc)
                                  .projects(employee.getProjects())
                                  .build();
                        })
                .collect(Collectors.toList());
        System.out.println("Employee salary after increasing by " + percentage);
        printEmployeeData(increasedEmployeeList);
    }

    // Filter Map Example
    // Filter Employee whose salary is grater than X then increase by Y percentage and find first
    // employee
    public static void increaseEmployeeSalaryAndFindFirst(
            List<Employee> employeeList, Double salary, Double percentage) {
        Double increaseByPerc = (percentage / 100) + 1;
        Optional<Employee> emp = employeeList
                .stream()
                .filter(employee -> employee.getSalary() > salary)
                .map(
                        employee ->
                                Employee.builder()
                                        .firstName(employee.getFirstName())
                                        .lastName(employee.getLastName())
                                        .salary(employee.getSalary() * increaseByPerc)
                                        .projects(employee.getProjects())
                                        .build())
                .findFirst();
        System.out.println("\n First Employee  " + emp);
    }

    // Flatmap Example
    public static void getProjectList(List<Employee> employeeList) {
        Set<String> projects = employeeList
                .stream()
                .map(employee -> employee.getProjects())
                .flatMap(projectList -> projectList.stream())
                .collect(Collectors.toSet());
        System.out.println("Projects " + projects);
    }

    // short circuit operation
    // SKIP,LIMIT Example
    public static void removeFirst_N_employeeAndSelectOnly_M_FromList(
            List<Employee> employeeList, int n, int m) {
        List<Employee> empList = employeeList
                .stream()
                .skip(n)
                .limit(m)
                .collect(Collectors.toList());
        printEmployeeData(empList);
    }

    // SORTED Example
    public static void sortEmployeeByFirstName(List<Employee> emp) {
        List<Employee> sortedEmployeeList = emp
                .stream()
                .sorted((e1, e2) -> e1.getFirstName().compareToIgnoreCase(e2.getFirstName()))
                .collect(Collectors.toList());
        System.out.println("Employee after sorting");
        printEmployeeData(sortedEmployeeList);
    }

    // Min Max example
    public static void minMaxSalary(List<Employee> employeeList) {
        Double min = employeeList
                .stream()
                .min(Comparator.comparing(Employee::getSalary))
                .get()
                .getSalary();
        Double max = employeeList
                .stream()
                .max(Comparator.comparing(Employee::getSalary))
                .orElseThrow(NoSuchElementException::new)
                .getSalary();
        System.out.println("MIN Salary " + min + " MAX Salary " + max);
    }

    // Reduce example
    public static void totalSalary(List<Employee> employeeList) {
        Double totalSalary = employeeList
                .stream()
                .map(e -> e.getSalary())
                .reduce(0.0, Double::sum);
        System.out.println("Total Salary " + totalSalary);
    }

    public static void main(String[] args) {
        // SpringApplication.run(Java8Application.class, args);
        List<Employee> employeeList = getEmployee();
        printEmployeeData(employeeList);
        increaseSalary(employeeList, 50.0);
        filterEmployeeBySalaryGreaterThanX(employeeList, 20000.0);
        increaseEmployeeSalary(employeeList, 20000.0, 20.0);
        increaseEmployeeSalaryAndFindFirst(employeeList, 20000.0, 20.0);
        getProjectList(employeeList);
        removeFirst_N_employeeAndSelectOnly_M_FromList(employeeList, 2, 2);
        sortEmployeeByFirstName(employeeList);
        minMaxSalary(employeeList);
        totalSalary(employeeList);
    }
}
