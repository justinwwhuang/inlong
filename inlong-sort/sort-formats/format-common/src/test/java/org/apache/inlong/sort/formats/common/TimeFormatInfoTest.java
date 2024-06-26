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

package org.apache.inlong.sort.formats.common;

import org.apache.inlong.common.pojo.sort.dataflow.field.format.FormatInfo;
import org.apache.inlong.common.pojo.sort.dataflow.field.format.TimeFormatInfo;

import org.junit.Test;

import java.sql.Time;
import java.text.ParseException;
import java.util.Collection;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for {@link TimeFormatInfo}.
 */
public class TimeFormatInfoTest extends FormatInfoTestBase {

    @Override
    Collection<FormatInfo> createFormatInfos() {
        return Collections.singletonList(new TimeFormatInfo("hh:mm:ss"));
    }

    @Test
    public void testSerialize() {
        Time time = Time.valueOf("11:12:13");

        assertEquals("11:12:13", new TimeFormatInfo("hh:mm:ss").serialize(time));
        assertEquals("13/12/11", new TimeFormatInfo("ss/mm/hh").serialize(time));
        assertEquals("11:12:13", new TimeFormatInfo().serialize(time));
    }

    @Test
    public void testDeserialize() throws ParseException {
        Time time = Time.valueOf("11:12:13");

        assertEquals(time, new TimeFormatInfo("hh:mm:ss").deserialize("11:12:13"));
        assertEquals(time, new TimeFormatInfo("ss/mm/hh").deserialize("13/12/11"));
        assertEquals(time, new TimeFormatInfo().deserialize("11:12:13"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidFormat() {
        new TimeFormatInfo("MINUTES");
    }

    @Test(expected = ParseException.class)
    public void testUnmatchedText() throws ParseException {
        new TimeFormatInfo("HH-mm-ss").deserialize("11:12:13");
    }
}
