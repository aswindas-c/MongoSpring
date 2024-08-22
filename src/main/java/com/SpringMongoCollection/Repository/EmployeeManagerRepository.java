package com.SpringMongoCollection.Repository;

import java.time.Instant;
import java.util.List;

import com.SpringMongoCollection.Model.EmployeeManager;

public interface EmployeeManagerRepository {
    List<EmployeeManager> findByManagerIdAndDateOfJoiningBefore(Integer managerId, Instant minJoiningDate);
    List<EmployeeManager> findByManagerId(Integer managerId);
    List<EmployeeManager> findByDateOfJoiningBefore(Instant minJoiningDate);
    List<EmployeeManager> findByDepartment(String department);
    Integer findMaxId();
    EmployeeManager save(EmployeeManager employeeManager);
    EmployeeManager insert(EmployeeManager employeeManager);
    void delete(EmployeeManager employeeManager);
    boolean existsById(Integer id);
    EmployeeManager findById(Integer id);
    List<EmployeeManager> findAll();
}
