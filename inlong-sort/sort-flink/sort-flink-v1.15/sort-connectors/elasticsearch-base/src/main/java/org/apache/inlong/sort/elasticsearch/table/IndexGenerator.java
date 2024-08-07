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

package org.apache.inlong.sort.elasticsearch.table;

import org.apache.flink.table.data.RowData;
import org.apache.flink.types.Row;

import java.io.Serializable;

/** This interface is responsible to generate index name from given {@link Row} record. */
public interface IndexGenerator extends Serializable {

    /**
     * Initialize the index generator, this will be called only once before {@link
     * #generate(RowData)} is called.
     */
    default void open() {
    }

    /** Generate index name according the the given row. */
    String generate(RowData row);
}
