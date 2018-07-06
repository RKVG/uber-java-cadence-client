/*
 *  Copyright 2012-2016 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 *  Modifications copyright (C) 2017 Uber Technologies, Inc.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"). You may not
 *  use this file except in compliance with the License. A copy of the License is
 *  located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 *  or in the "license" file accompanying this file. This file is distributed on
 *  an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 *  express or implied. See the License for the specific language governing
 *  permissions and limitations under the License.
 */

package com.uber.cadence.python;

import static org.junit.Assert.assertEquals;

import java.io.StringWriter;
import org.junit.Test;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

public class PythonTest {

  @Test
  public void test() {
    PythonInterpreter interp = new PythonInterpreter();
    StringWriter out = new StringWriter();
    interp.setOut(out);
    interp.exec("import sys");
    interp.exec("import hello");
    interp.exec("hello.helloworld()");
    interp.set("a", new PyInteger(42));
    interp.exec("print a");
    interp.exec("x = 2+2");
    PyObject x = interp.get("x");
    assertEquals("hello world\n42", out.toString().trim());
    assertEquals(4, x.asInt());
  }
}
