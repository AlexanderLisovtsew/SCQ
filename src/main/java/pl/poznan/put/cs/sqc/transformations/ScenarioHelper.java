package pl.poznan.put.cs.sqc.transformations;

import pl.poznan.put.cs.sqc.model.Step;

import java.util.List;

/**
 * Helper class for scenarios.
 * Author: Aliaksandr Lisoutau
 */
public class ScenarioHelper {

    private ScenarioHelper() {
    }

    /**
     * Checks if a step is a special step.
     *
     * @param step The step to be checked.
     * @return true if the step is a special step.
     */
    public static boolean isSpecialStep(Step step) {
        return step.getContent().startsWith("IF") || step.getContent().startsWith("FOR EACH");
    }

    /**
     * Checks if a given step does not begin with an actor's name.
     *
     * @param step   The step to be checked.
     * @param actors The list of actors.
     * @return true if the step does not begin with any actor's name.
     */
    public static boolean notBeginsWithActorsName(Step step, List<String> actors) {
        return actors.stream().noneMatch(a -> step.getContent().startsWith(a));
    }
}
