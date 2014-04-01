/*
 * Copyright 2013 original author or authors.
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
package pl.allegro.tdr.gruntmaven;

import pl.allegro.tdr.gruntmaven.executable.Executable;
import java.util.Arrays;
import java.util.List;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

/**
 * Executes npm install to download all dependencies declared in
 * package.json.
 *
 * @author Adam Dubiel
 */
@Mojo(name = "npm", defaultPhase = LifecyclePhase.TEST)
public class ExecNpmMojo extends AbstractExecutableMojo {

    protected static final String NPM_INSTALL_COMMAND = "install";

    /**
     * Name of npm executable in PATH, defaults to npm.
     */
    @Parameter(property = "npmExecutable", defaultValue = "npm")
    protected String npmExecutable;

    @Override
    protected List<Executable> getExecutables() {
        Executable executable = new Executable(npmExecutable);

        executable.addArgument(NPM_INSTALL_COMMAND);
        appendNoColorsArgument(executable);

        return Arrays.asList(executable);
    }

    protected void appendNoColorsArgument(Executable executable) {
        if (!showColors) {
            executable.addArgument("--color=false");
        }
    }
}
