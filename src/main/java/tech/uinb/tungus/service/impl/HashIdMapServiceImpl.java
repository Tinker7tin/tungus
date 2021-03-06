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
package tech.uinb.tungus.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.uinb.tungus.entity.HashIdMap;
import tech.uinb.tungus.entity.ObjType;
import tech.uinb.tungus.service.HashIdMapService;
import tech.uinb.tungus.service.Splitter;
import tech.uinb.tungus.service.TableMetaService;
import tech.uinb.tungus.repository.HashIdMapRepository;

import javax.annotation.PostConstruct;

@Service
@Transactional
public class HashIdMapServiceImpl implements HashIdMapService {
    @Autowired
    private HashIdMapRepository hashIdMapRepository;
    @Autowired
    private TableMetaService tableMetaService;
    private Splitter splitter;

    @Override
    public void save(String key, long id, ObjType type) {
        var detail = splitter.computeTable(key);
        hashIdMapRepository.save(key, id, type, detail.tableName());
    }

    @Override
    public HashIdMap getByHash(String hash) {
        var detail = splitter.computeTable(hash);
        return hashIdMapRepository.queryByHash(hash, detail.tableName());
    }

    @Override
    public HashIdMap getByBlockNumber(long id) {
        var detail = splitter.computeTable(String.valueOf(id));
        return hashIdMapRepository.queryByBlockNumber(id,detail.tableName());
    }

    @PostConstruct
    public void init() {
        splitter = new StringSplitter(tableMetaService.getByPrefix(TableMetaService.KEY_ID_MAP));
    }
}
