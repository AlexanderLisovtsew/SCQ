package pl.poznan.put.cs.sqc.transformations;

import pl.poznan.put.cs.sqc.model.Scenario;

/** Main strategy class
 * @author Kamil Pluciński
 */
public abstract class ScenarioStrategy {

    /**
     * @param scenario This method is responsible for proccessing scenario when user want to count number of steps
     */
    public abstract int processScenario(Scenario scenario);
}
