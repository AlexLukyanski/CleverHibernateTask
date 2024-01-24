package ru.clevertec.house.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.clevertec.house.entity.Person;
import ru.clevertec.house.util.constant.Sex;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Component
public class JdbcPersonMapper implements RowMapper<Person> {

    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        Person person = new Person();
        person.setId(rs.getInt("id"));
        person.setUuid(UUID.fromString(rs.getString("uuid")));
        person.setName(rs.getString("name"));
        person.setSurname(rs.getString("surname"));
        person.setSex(Sex.valueOf(rs.getString("sex")));
        person.setPassportSeries(rs.getString("passport_series"));
        person.setPassportNumber(rs.getString("passport_number"));
        person.setCreateDate(rs.getTimestamp("create_date").toLocalDateTime());
        person.setUpdateDate(rs.getTimestamp("update_date").toLocalDateTime());
        return person;
    }
}
