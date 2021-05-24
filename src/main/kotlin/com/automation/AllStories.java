package com.automation;

import com.automation.jbehave.LoggingStoryReporter;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.embedder.StoryControls;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporter;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.ParameterControls;
import org.jbehave.core.steps.ParameterConverters;
import org.jbehave.core.steps.spring.SpringStepsFactory;
import org.junit.Ignore;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.LinkedList;
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
                .useStoryReporterBuilder(getStoryReporterBuilder())
                .useStoryControls(new StoryControls()
                                    .doResetStateBeforeScenario(true)
                                    .doIgnoreMetaFiltersIfGivenStory(true))
                .useParameterControls(new ParameterControls()
                                    .useDelimiterNamedParameters(true))
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

    protected StoryReporterBuilder getStoryReporterBuilder() {
        return new StoryReporterBuilder()
                .withDefaultFormats()
                .withReporters(getReporters())
                .withFailureTrace(true)
                .withFormats( Format.CONSOLE, Format.STATS, Format.HTML )
                .withCodeLocation(codeLocationFromClass(this.getClass()));
    }

    protected StoryReporter[] getReporters() {

        List<StoryReporter> reporters = new LinkedList<>();
        reporters.add(new LoggingStoryReporter());


        return reporters.toArray(new StoryReporter[reporters.size()]);
    }

}
