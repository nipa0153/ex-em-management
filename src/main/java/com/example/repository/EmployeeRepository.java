package com.example.repository;
/**
 * 従業員情報を操作するリポジトリ.
 * 
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Employee;

@Repository

public class EmployeeRepository {
    
    @Autowired
    private NamedParameterJdbcTemplate template;

    public static final RowMapper<Employee> EMPLOYEE_ROW_MAPPER 
    = (rs, i) -> {
        Employee employee = new Employee();
        employee.setId(rs.getInt("id"));
        employee.setName(rs.getString("name"));
        employee.setImage(rs.getString("image"));
        employee.setGender(rs.getString("gender"));
        employee.setHireDate(rs.getDate("hire_date"));
        employee.setMailAddress(rs.getString("mail_address"));
        employee.setZipCode(rs.getString("zip_code"));
        employee.setAddress(rs.getString("address"));
        employee.setTelephone(rs.getString("telephone"));
        employee.setSalary(rs.getInt("salary"));
        employee.setCharacteristics(rs.getString("characteristic"));
        employee.setDependentsCount(rs.getInt("dependents_count"));
        return employee;
    }
    ;

    /**
     * 従業員情報を全件検索する.
     * 
     */
    public static final String SQL_FIND_ALL = """
                        SELECT
                            name,
                            image,
                            gender,
                            hire_date,
                            mail_address,
                            zip_code,
                            address,
                            telephone,
                            salary,
                            characteristic,
                            dependents_count
                        FROM
                            employees
                        ORDER BY
                            hire_date DESC;
                        """;


    /**
     * 主キーから従業員情報を取得する.
     * 
     */ 
    public static final String SQL_LOAD = """
                        SELECT
                            id,
                            name
                        FROM
                            employees
                        WHERE
                            id = :id;
                        """;

    /**
     * 従業員情報を登録する.
     * 
     */
    public static final String SQL_UPDATE = """
                        UPDATE
                            employees
                        SET
                            dependents_count = :dependentsCount
                        WHERE
                            id = :id;            
                        """;

    public List<Employee> findAll(){
        List<Employee> employeeList = template.query(SQL_FIND_ALL, EMPLOYEE_ROW_MAPPER);
        return employeeList;
    }

    public Employee load(Integer id){
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
        Employee employee = template.queryForObject(SQL_LOAD, param, EMPLOYEE_ROW_MAPPER);
        return employee;
    }

    public void update(Employee employee){
        SqlParameterSource param = 
        new BeanPropertySqlParameterSource(employee);
        template.update(SQL_UPDATE, param);
    }
}
