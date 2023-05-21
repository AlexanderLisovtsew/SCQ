package pl.poznan.put.cs.sqc.transformations;

import pl.poznan.put.cs.sqc.model.Scenario;
import pl.poznan.put.cs.sqc.model.Step;

import java.util.List;

/**
 * Scenario strategy for counting all steps.
 * Author: Aliaksandr Lisoutau
 */
public class CountStepsScenarioStrategy extends ScenarioStrategy {

    /**
     * Processes the scenario and counts the number of steps.
     *
     * @param scenario The scenario to be processed.
     * @return The total number of steps in the scenario.
     */
    @Override
    public int processScenario(Scenario scenario) {
        return countSteps(scenario.getSteps());
    }

    /**
     * Counts the number of steps in the given list of steps, including substeps recursively.
     *
     * @param steps The list of steps to be counted.
     * @return The total number of steps.
     */
    private int countSteps(List<Step> steps) {
        long deepCount = steps.stream()
                .filter(ScenarioHelper::isSpecialStep)
                .map(step -> countSteps(step.getSubSteps()))
                .mapToLong(Integer::intValue)
                .sum();
        return (int) (steps.size() + deepCount);
    }
}
