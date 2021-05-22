package com.automation;

import com.automation.api.service.PetStoreService;
import com.automation.api.steps.PetSteps;
import com.automation.api.steps.UserSteps;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.jbehave.core.steps.ParameterConverters;
import org.jbehave.core.steps.Steps;
import org.junit.Ignore;

import java.util.LinkedList;
import java.util.List;

import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;
import static org.jbehave.core.reporters.Format.CONSOLE;

@Ignore
public class AllStories extends JUnitStories {

    @Override
    public Configuration configuration() {
        return new MostUsefulConfiguration()
                .useStoryLoader(new LoadFromClasspath(this.getClass()))
                .useStoryReporterBuilder(new StoryReporterBuilder()
                        .withCodeLocation(codeLocationFromClass(this.getClass()))
                        .withFormats(CONSOLE))
                .useParameterConverters(getConverters());
    }

    protected ParameterConverters getConverters() {
        return new ParameterConverters();
    }

    @Override
    public InjectableStepsFactory stepsFactory() {
        List<Steps> stepsFileList = new LinkedList<>();
        PetStoreService petStoreService = new PetStoreService();
        stepsFileList.add(new UserSteps(petStoreService));
        stepsFileList.add(new PetSteps(petStoreService));
        return new InstanceStepsFactory(configuration(), stepsFileList);
    }

    @Override
    protected List<String> storyPaths() {
        return new StoryFinder().findPaths(
                codeLocationFromClass(
                        this.getClass()
                ),
                "**/*.story",
                ""
        );
    }

    @Override
    public Embedder configuredEmbedder() {
        return super.configuredEmbedder();
    }

}
