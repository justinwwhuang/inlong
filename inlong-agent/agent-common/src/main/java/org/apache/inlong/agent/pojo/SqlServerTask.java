/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.inlong.agent.pojo;

import lombok.Data;

@Data
public class SqlServerTask {

    private String hostname;
    private String user;
    private String password;
    private String port;
    private String serverName;
    private String dbname;
    private String schemaName;
    private String tableName;
    private String serverTimezone;
    private String unixTimestampFormatEnable;

    private SqlServerTask.Snapshot snapshot;
    private SqlServerTask.Offset offset;
    private SqlServerTask.History history;

    @Data
    public static class Offset {

        private String intervalMs;
        private String filename;
        private String specificOffsetFile;
        private String specificOffsetPos;
    }

    @Data
    public static class Snapshot {

        private String mode;
    }

    @Data
    public static class History {

        private String filename;
    }

    @Data
    public static class SqlserverTaskConfig {

        private String hostname;
        private String username;
        private String password;
        private String port;
        private String database;
        private String schemaName;
        private String tableName;
        private String serverTimezone;
        private String unixTimestampFormatEnable;

        private String snapshotMode;
        private String intervalMs;
        private String offsetFilename;
        private String historyFilename;

        private String specificOffsetFile;
        private String specificOffsetPos;
    }

}
