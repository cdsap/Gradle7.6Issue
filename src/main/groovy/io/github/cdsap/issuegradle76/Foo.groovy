package io.github.cdsap.issuegradle76

import com.android.build.gradle.tasks.MergeSourceSetFolders
import groovy.transform.CompileStatic
import org.gradle.api.Project
import org.gradle.api.Task

@CompileStatic
class Foo {
    void apply(Project project) {
        project.tasks.withType(MergeSourceSetFolders).configureEach { Task task ->
            task.outputs.doNotCacheIf("", { true })
        }
    }
}
