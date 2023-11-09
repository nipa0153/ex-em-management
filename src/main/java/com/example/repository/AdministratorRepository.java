package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
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

    public static final String SQL_ADMIN_SELECT = """
                        SELECT 
                            mail_address, 
                            password 
                        FROM 
                            administrators;
                        """;
    
    public static final String SQL_INSERT = """
                        INSERT INTO administrators(
                            name,
                            mailAddress,
                            password
                            )
                        VALUES(
                            :name,
                            :mainAddress,
                            :password
                            );
                        """;

    public void insert(Administrator administrator){
        SqlParameterSource param  = new BeanPropertySqlParameterSource(administrator);
        template.update(SQL_INSERT, param);

    }

    public List<Administrator> findByMailAddressAndPassword(String mailAddress, String password){
        SqlParameterSource param = new MapSqlParameterSource().addValue(mailAddress, password);
        List<Administrator> administratorList = template.query(SQL_ADMIN_SELECT, ADMIN_ROW_MAPPER);
    if (administratorList.size() == 0) {
        return null;
    }
    return administratorList.get(0);
    
    }
}
