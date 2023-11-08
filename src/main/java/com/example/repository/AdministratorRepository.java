package com.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.domain.Administrator;


/**
 * 管理者情報を操作するリポジトリ.
 * 
 */

@Repository 
public class AdministratorRepository {
    @Autowired
    private NamedParameterJdbcTemplate template;

    private static final RowMapper<Administrator> ADMIN_ROW_MAPPER 
    = (rs, i) -> {
        Administrator administrator = new Administrator();
        administrator.setId(rs.getInt("id"));
        administrator.setName(rs.getString("name"));
        administrator.setMailAddress(rs.getString("mail_address"));
        administrator.setPassword(rs.getString("password"));

        return administrator;
    };

    public void insert(){

    }


}
