package pl.poznan.put.cs.sqc.service;

import org.springframework.stereotype.Service;
import pl.poznan.put.cs.sqc.model.Step;
import pl.poznan.put.cs.sqc.transformations.ScenarioHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Aliaksandr Lisoutau
 */

@Service
public class RetrieveStepsWithoutActorsNameAtTheBeginningService {

    /**
     * Retrieves a list of steps without actors' names at the beginning.
     *
     * @param steps     the list containing every step from the scenario
     * @param allActors the list of actors to keep
     * @return          the list of steps without actors' names
     */
    public List<Step> retrieveStepsWithoutActorsNameAtTheBeginning(List<Step> steps, List<String> allActors) {
        List<Step> resultList = new ArrayList<>();
        for (Step s : steps) {
            if (ScenarioHelper.notBeginsWithActorsName(s, allActors) && !ScenarioHelper.isSpecialStep(s)) {
                resultList.add(s);
            } else if (ScenarioHelper.isSpecialStep(s)) {
                resultList.addAll(retrieveStepsWithoutActorsNameAtTheBeginning(s.getSubSteps(), allActors));
            }
        }
        return resultList;
    }

    /**
     * Numbers the steps of the scenario.
     *
     * @param steps  the list of steps
     * @param prefix the prefix to add before each step number
     * @return       the numbered scenario as a string
     */
    public String numberTheStepsOfTheScenario(List<Step> steps, String prefix) {
        StringBuilder numeratedScenario = new StringBuilder();
        StringBuilder subprefix = new StringBuilder("\t" + prefix);
        int currentNumber = 1;
        for (Step s : steps) {
            numeratedScenario.append(prefix + currentNumber + ". ");
            numeratedScenario.append(s.getContent() + "\n");
            if (s.getSubSteps().size() != 0) {
                numeratedScenario.append(numberTheStepsOfTheScenario(s.getSubSteps(), subprefix.append(currentNumber + ".").toString()));
            }
            currentNumber += 1;
        }
        return numeratedScenario.toString();
    }
}
