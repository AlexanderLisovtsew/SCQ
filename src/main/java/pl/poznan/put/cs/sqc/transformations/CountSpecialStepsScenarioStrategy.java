package pl.poznan.put.cs.sqc.transformations;

import pl.poznan.put.cs.sqc.model.Scenario;
import pl.poznan.put.cs.sqc.model.Step;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Scenario strategy for counting special steps.
 * Author: Aliaksandr Lisoutau
 */
public class CountSpecialStepsScenarioStrategy extends ScenarioStrategy {

    /**
     * Processes the scenario and counts the number of special steps.
     *
     * @param scenario The scenario to be processed.
     * @return The total number of special steps in the scenario.
     */
    @Override
    public int processScenario(Scenario scenario) {
        return countSpecialSteps(scenario.getSteps());
    }

    /**
     * Counts the number of special steps in the given list of steps, including substeps recursively.
     *
     * @param steps The list of steps to be counted.
     * @return The total number of special steps.
     */
    private int countSpecialSteps(List<Step> steps) {
        return steps.stream()
                .filter(ScenarioHelper::isSpecialStep)
                .map(step -> countSpecialSteps(step.getSubSteps()) + 1)
                .mapToInt(Integer::intValue)
                .sum();
    }
}
