package com.test_task.dao;

import com.test_task.model.Department;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentDao extends CrudRepository<Department, Long> {

    //TODO Get a list of department IDS where the number of employees doesn't exceed 3 people
    @Query(
            value = "SELECT d.id from department as d inner join employee as e on d.id =e.department_id" +
                    " group by d.id having count(e.id) != 3",
            nativeQuery = true)
    List<Long> findAllWhereDepartmentDoesntExceedThreePeople();

    //TODO Get a list of departments IDs with the maximum total salary of employees
    @Query(
            value = "SELECT d.id from employee as e inner join department as d " +
                    "on e.department_id = d.id group by d.id order by sum(e.salary) desc",
            nativeQuery = true)
    List<Long> findAllByMaxTotalSalary();
}
