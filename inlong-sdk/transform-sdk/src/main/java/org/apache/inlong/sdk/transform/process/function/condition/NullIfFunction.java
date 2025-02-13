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

package org.apache.inlong.sdk.transform.process.function.condition;

import org.apache.inlong.sdk.transform.decode.SourceData;
import org.apache.inlong.sdk.transform.process.Context;
import org.apache.inlong.sdk.transform.process.function.FunctionConstant;
import org.apache.inlong.sdk.transform.process.function.TransformFunction;
import org.apache.inlong.sdk.transform.process.operator.OperatorTools;
import org.apache.inlong.sdk.transform.process.parser.ValueParser;

import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.Function;

import java.util.List;

/**
 * NullIfFunction  ->  NULLIF(expr1,expr2)
 * description:
 * - Return NULL if expr1 = expr2 is true
 * - Return expr1 otherwise
 */
@TransformFunction(type = FunctionConstant.CONDITION_TYPE, names = {
        "nullif",
        "null_if"
}, parameter = "(Expr expr1, Expr expr2)", descriptions = {
        "- Return \"\" if 'expr1' = 'expr2' is true;",
        "- Return 'expr1' otherwise."
}, examples = {"nullif(5, 3) = 5"})
public class NullIfFunction implements ValueParser {

    private final ValueParser firstExprParser;
    private final ValueParser secondExprParser;

    public NullIfFunction(Function expr) {
        List<Expression> expressions = expr.getParameters().getExpressions();
        firstExprParser = OperatorTools.buildParser(expressions.get(0));
        secondExprParser = OperatorTools.buildParser(expressions.get(1));
    }

    @Override
    public Object parse(SourceData sourceData, int rowIndex, Context context) {
        Object firstExprObj = firstExprParser.parse(sourceData, rowIndex, context);
        if (firstExprObj == null) {
            return null;
        }
        Object secondExprObj = secondExprParser.parse(sourceData, rowIndex, context);
        if (secondExprObj == null) {
            return firstExprObj;
        }
        int cmp = OperatorTools.compareValue((Comparable) firstExprObj, (Comparable) secondExprObj);
        return cmp == 0 ? null : firstExprObj;
    }
}
