package edu.illinois.nondex.gradle.plugin

import edu.illinois.nondex.gradle.plugin.tasks.NonDexHelp
import edu.illinois.nondex.gradle.plugin.tasks.NonDexTest
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.ProviderFactory
import org.gradle.api.tasks.testing.Test

import javax.inject.Inject

import static org.gradle.testretry.internal.config.TestTaskConfigurer.configureTestTask

public class NonDexPlugin implements Plugin<Project> {

    private final ObjectFactory objectFactory
    private final ProviderFactory providerFactory

    @Inject
    NonDexPlugin(ObjectFactory objectFactory, ProviderFactory providerFactory) {
        this.objectFactory = objectFactory;
        this.providerFactory = providerFactory;
    }

    @Override
    public void apply(Project project) {
        Test nondex = project.tasks.create(NonDexTest.NAME, NonDexTest)
        nondex.init()
        configureTestTask(nondex, objectFactory, providerFactory);

        // project.tasks.create(NonDexHelp.NAME, NonDexHelp).init()
        // configureTestTask(nondex, objectFactory, providerFactory)
        // project.getTasks()
        //     .withType(Test.class)
        //     .configureEach(task -> configureTestTask(task, objectFactory, providerFactory));
    }
}
