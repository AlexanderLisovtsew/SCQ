package pl.poznan.put.cs.sqc.transformations;

import pl.poznan.put.cs.sqc.model.Scenario;

/** Main strategy class
 * @author Aliaksandr Lisoutau
 */
public abstract class ScenarioStrategy {

    /**
     * @param scenario: This function handles the scenario where the user wants to calculate the total number of steps.
     */
    public abstract int processScenario(Scenario scenario);
}
