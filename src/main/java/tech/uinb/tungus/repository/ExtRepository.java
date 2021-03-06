/**
 * Copyright 2021 UINB Technologies Pte. Ltd.
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package tech.uinb.tungus.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import tech.uinb.tungus.entity.Ext;

import java.io.ByteArrayInputStream;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class ExtRepository {
    @Autowired
    private JdbcTemplate template;

    public void save(long id, byte[] data, String tableName) {
        template.update(con -> {
            var stat = con.prepareStatement("insert into " + tableName + " (id, data) values (?, ?)");
            stat.setLong(1, id);
            stat.setBlob(2, new ByteArrayInputStream(data));
            return stat;
        });
    }

    public Ext queryExtId(long id, String tableName){
        return template.query(con -> {
            var stat = con.prepareStatement("select * from "+tableName+" where id = ?");
            stat.setLong(1, id);
            return stat;
        }, extractor);
    }


    private final ExtRepository.ExtResultExtractor extractor = new ExtRepository.ExtResultExtractor();

    private class ExtResultExtractor implements ResultSetExtractor<Ext> {

        @Override
        public Ext extractData(ResultSet rs) throws SQLException, DataAccessException {
            if (rs.next()) {
                Ext ext = new Ext();
                ext.setData(rs.getBytes("data"));
                ext.setId(rs.getLong("id"));
                return ext;
            }
            return null;
        }
    }
}
