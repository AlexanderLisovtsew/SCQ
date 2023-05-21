package pl.poznan.put.cs.sqc.service;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import lombok.extern.slf4j.Slf4j;
import pl.poznan.put.cs.sqc.model.Scenario;
import pl.poznan.put.cs.sqc.model.Step;
import pl.poznan.put.cs.sqc.transformations.ScenarioHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Author: Aliaksandr Lisoutau
 */

@Slf4j
public class GetOneActorStepsService {

    /**
     * Returns a pretty JSON array with steps that belong only to the actor provided as an argument.
     *
     * @param scenario the scenario
     * @param actor    the actor for which to retrieve steps
     * @return         the JSON array of steps belonging to the actor
     */
    public String getListOfActorsSteps(Scenario scenario, String actor) {
        JsonArray actorsListJson = new JsonArray();
        List<Step> flatStepsList = new ArrayList<>();
        createFlatListOfSteps(scenario.getSteps(), flatStepsList);
        log.info("Created flat list of actor steps");
        flatStepsList.removeIf(step -> ScenarioHelper.notBeginsWithActorsName(step, Collections.singletonList(actor)));
        flatStepsList.forEach(step -> actorsListJson.add(step.getContent()));
        log.info("Removed steps which did not belong to actor");
        return new GsonBuilder()
                .setPrettyPrinting()
                .create()
                .toJson(actorsListJson);
    }

    /**
     * Flattens the steps and substeps into a flat list.
     *
     * @param steps          the steps to flatten
     * @param flatStepsList  the output list where the flattened steps will be saved
     */
    void createFlatListOfSteps(List<Step> steps, List<Step> flatStepsList) {
        for (Step step : steps) {
            if (ScenarioHelper.isSpecialStep(step)) {
                createFlatListOfSteps(step.getSubSteps(), flatStepsList);
            }
            flatStepsList.add(step);
        }
    }

}
