package pl.poznan.put.cs.sqc.transformations;

import pl.poznan.put.cs.sqc.model.Scenario;
import pl.poznan.put.cs.sqc.model.Step;

import java.util.ArrayList;
import java.util.List;

/**
 * Scenario strategy for counting steps without actor's name at the beginning.
 * Author: Aliaksandr Lisoutau
 */
public class CountStepsWithoutActorsNameAtTheBeginningScenarioStrategy extends ScenarioStrategy {

    /**
     * Processes the scenario and counts the number of steps without actors' names at the beginning.
     *
     * @param scenario The scenario to be processed.
     * @return The number of steps without actors' names at the beginning.
     */
    @Override
    public int processScenario(Scenario scenario) {
        List<String> allActors = new ArrayList<>(scenario.getActors());
        allActors.add(scenario.getSystemActor());
        return countStepsWithoutActorsNameAtTheBeginning(scenario.getSteps(), allActors);
    }

    /**
     * Counts the number of steps without actors' names at the beginning.
     *
     * @param steps     The list of steps to be processed.
     * @param allActors The list of all actors.
     * @return The number of steps without actors' names at the beginning.
     */
    private int countStepsWithoutActorsNameAtTheBeginning(List<Step> steps, List<String> allActors) {
        int counter = 0;

        for (Step s : steps) {
            if (ScenarioHelper.notBeginsWithActorsName(s, allActors) && !ScenarioHelper.isSpecialStep(s)) {
                counter++;
            } else if (ScenarioHelper.isSpecialStep(s)) {
                counter += countStepsWithoutActorsNameAtTheBeginning(s.getSubSteps(), allActors);
            }
        }

        return counter;
    }
}
