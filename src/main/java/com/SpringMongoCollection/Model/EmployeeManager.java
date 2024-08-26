package com.SpringMongoCollection.Model;

import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "employeeManager")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeManager {
    
    @Id
    private Integer id;

    private String name;
    
    private String designation;

    private String email;

    private String department;

    private String mobile;

    private String location;

    private Integer managerId;

    private Instant dateOfJoining;

    private Instant createdTime;

    private Instant updatedTime;

}
