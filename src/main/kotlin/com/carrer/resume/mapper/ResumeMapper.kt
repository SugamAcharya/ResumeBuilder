package com.carrer.resume.mapper

import com.carrer.resume.model.Resume
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet


class ResumeMapper: RowMapper<Resume> {
    override fun mapRow(rs: ResultSet, rowNum: Int): Resume? {
        return null;
    }
}