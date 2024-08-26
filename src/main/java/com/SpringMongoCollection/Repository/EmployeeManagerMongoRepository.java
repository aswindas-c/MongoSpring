package com.SpringMongoCollection.Repository;

import java.time.Instant;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.SpringMongoCollection.Model.EmployeeManager;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

@Repository
public class EmployeeManagerMongoRepository implements EmployeeManagerRepository{

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<EmployeeManager> findByManagerIdAndDateOfJoiningBefore(Integer managerId, Instant minJoiningDate) {
        // Create query
        Query query = new Query();

        // Add criteria
        Criteria criteria = new Criteria();
        criteria.and("managerId").is(managerId);
        criteria.and("dateOfJoining").lte(minJoiningDate);

        query.addCriteria(criteria);

        // Execute query and return results
        return mongoTemplate.find(query, EmployeeManager.class);
    }

    public List<EmployeeManager> findByManagerId(Integer managerId) {
        // Create a new Query object
        Query query = new Query();

        // Define the criteria for the managerId
        Criteria criteria = Criteria.where("managerId").is(managerId);

        // Add the criteria to the query
        query.addCriteria(criteria);

        // Execute the query and return the results
        return mongoTemplate.find(query, EmployeeManager.class);
    }

    public List<EmployeeManager> findByDateOfJoiningBefore(Instant minJoiningDate) {
        // Create a new Query object
        Query query = new Query();

        // Define the criteria for yearsOfExperience
        Criteria criteria = Criteria.where("dateOfJoining").lte(minJoiningDate);

        // Add the criteria to the query
        query.addCriteria(criteria);

        // Execute the query and return the results
        return mongoTemplate.find(query, EmployeeManager.class);
    }

    public List<EmployeeManager> findByDepartment(String department) {
        // Create a new Query object
        Query query = new Query();

        // Define the criteria for department
        Criteria criteria = new Criteria();
        criteria.and("department").is(department);
        criteria.and("managerId").is(0);
        

        // Add the criteria to the query
        query.addCriteria(criteria);

        // Execute the query and return the results
        return mongoTemplate.find(query, EmployeeManager.class);
    }

    public Integer findMaxId() {
        GroupOperation groupById = Aggregation.group().max("_id").as("maxId");
        Aggregation aggregation = Aggregation.newAggregation(groupById);
        AggregationResults<MaxIdResult> results = mongoTemplate.aggregate(aggregation, "Employee", MaxIdResult.class);
        MaxIdResult maxIdResult = results.getUniqueMappedResult();
        return maxIdResult != null ? maxIdResult.getMaxId() : null;
    }

    // Inner class to map the aggregation result
    public static class MaxIdResult {
        private Integer maxId;

        public Integer getMaxId() {
            return maxId;
        }

        public void setMaxId(Integer maxId) {
            this.maxId = maxId;
        }
    }


    public EmployeeManager save(EmployeeManager employeeManager) {
        return mongoTemplate.save(employeeManager);
    }

    public EmployeeManager insert(EmployeeManager employeeManager) {
        return mongoTemplate.insert(employeeManager);
    }
    

    public void delete(EmployeeManager employeeManager) {
        mongoTemplate.remove(employeeManager);
    }

    public boolean existsById(Integer id) {
        Query query = new Query(Criteria.where("_id").is(id));
        return mongoTemplate.exists(query, EmployeeManager.class);
    }


    public EmployeeManager findById(Integer id) {
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, EmployeeManager.class);
    }

    public List<EmployeeManager> findAll() {
        return mongoTemplate.findAll(EmployeeManager.class);
    }
}