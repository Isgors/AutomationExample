package com.automation;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.ParameterConverters;
import org.jbehave.core.steps.spring.SpringStepsFactory;
import org.junit.Ignore;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;
import static org.jbehave.core.reporters.Format.CONSOLE;

@Ignore
public class AllStories extends JUnitStories {

    protected ApplicationContext applicationContext;

    public AllStories(){
        applicationContext = getContextInstance();
    }

    protected ApplicationContext getContextInstance() {
        if (applicationContext == null){
            this.applicationContext = new AnnotationConfigApplicationContext(AutomationConfiguration.class);
        }

        return applicationContext;
    }

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
        return new SpringStepsFactory(configuration(), getContextInstance());
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
