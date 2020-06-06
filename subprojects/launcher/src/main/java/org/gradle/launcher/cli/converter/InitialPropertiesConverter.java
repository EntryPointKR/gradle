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

package org.gradle.launcher.cli.converter;

import org.gradle.cli.CommandLineConverter;
import org.gradle.cli.CommandLineParser;
import org.gradle.cli.ParsedCommandLine;
import org.gradle.cli.SystemPropertiesCommandLineConverter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class InitialPropertiesConverter {
    private final CommandLineConverter<Map<String, String>> systemPropertiesCommandLineConverter = new SystemPropertiesCommandLineConverter();

    public void configure(CommandLineParser parser) {
        systemPropertiesCommandLineConverter.configure(parser);
    }

    public InitialProperties convert(ParsedCommandLine commandLine) {
        Map<String, String> requestedSystemProperties = systemPropertiesCommandLineConverter.convert(commandLine, new HashMap<>());

        return new InitialProperties() {
            @Override
            public Map<String, String> getRequestedSystemProperties() {
                return Collections.unmodifiableMap(requestedSystemProperties);
            }
        };
    }
}
