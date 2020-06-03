/*
 * Copyright 2020 the original author or authors.
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

package org.gradle.jvm.toolchain.internal

import org.gradle.api.internal.CollectionCallbackActionDecorator
import org.gradle.util.TestUtil
import spock.lang.Specification

class DefaultJavaInstallationContainerTest extends Specification {

    def "installation container has current vm initially"() {
        given:
        def container = new DefaultJavaInstallationContainer(TestUtil.instantiatorFactory().decorateLenient(), CollectionCallbackActionDecorator.NOOP)

        when:
        def installations = container.getAsMap().values()

        then:
        installations.size() == 1
        installations.first().path == System.getProperty("java.home")
    }

    def "installation has given name"() {
        given:
        def container = new DefaultJavaInstallationContainer(TestUtil.instantiatorFactory().decorateLenient(), CollectionCallbackActionDecorator.NOOP)

        when:
        container.create("someName")

        then:
        container.getByName("someName").name == "someName"
    }
}
