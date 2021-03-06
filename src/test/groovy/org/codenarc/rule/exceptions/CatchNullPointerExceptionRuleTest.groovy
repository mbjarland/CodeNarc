/*
 * Copyright 2008 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.codenarc.rule.exceptions

import org.codenarc.rule.AbstractRuleTestCase
import org.codenarc.rule.Rule

/**
 * Tests for CatchNullPointerExceptionRule
 *
 * @author Chris Mair
  */
class CatchNullPointerExceptionRuleTest extends AbstractRuleTestCase {

    void testRuleProperties() {
        assert rule.priority == 2
        assert rule.name == 'CatchNullPointerException'
    }

    void testApplyTo_Violation() {
        final SOURCE = '''
                try {
                    doSomething()
                }
                catch(NullPointerException t) {
                }
        '''
        assertSingleViolation(SOURCE, 5, 'catch(NullPointerException t) {')
    }

    void testApplyTo_Violation_FullPackageName() {
        final SOURCE = 'try {  } catch(java.lang.NullPointerException t) { }'
        assertSingleViolation(SOURCE, 1, 'catch(java.lang.NullPointerException t) {')
    }

    void testApplyTo_NoViolations() {
        final SOURCE = '''
            def myMethod() {
                try {
                } catch(Exception t) { }
            }
            '''
        assertNoViolations(SOURCE)
    }

    protected Rule createRule() {
        new CatchNullPointerExceptionRule()
    }

}