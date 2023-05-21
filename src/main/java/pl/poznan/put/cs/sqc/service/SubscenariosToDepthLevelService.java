package pl.poznan.put.cs.sqc.service;

import com.google.gson.GsonBuilder;
import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Service;
import pl.poznan.put.cs.sqc.model.Scenario;
import pl.poznan.put.cs.sqc.model.Step;
import pl.poznan.put.cs.sqc.transformations.ScenarioHelper;

import java.util.ArrayList;
import java.util.List;


/**
 * Author: Aliaksandr Lisoutau
 */

@Service
public class SubscenariosToDepthLevelService {
    /**
     * Returns a new scenario containing only the steps for the chosen depth level.
     *
     * @param scenario  the original scenario
     * @param maxLevel  the target depth level
     * @return          JSON representation of the new scenario
     */
    public String getSubscenariosToDepthLevel(Scenario scenario, Integer maxLevel) {
        JSONArray nestedSteps = new JSONArray();

        if (maxLevel < 0) {
            throw new ValueException("Level cannot be lower than 1");
        } else if (maxLevel > 0) {
            nestedSteps = createRecursivelyNestedStepsJson(scenario.getSteps(), 1, maxLevel);
        }

        JSONArray actorsJsonArray = new JSONArray();
        actorsJsonArray.addAll(scenario.getActors());

        JSONObject json = new JSONObject()
                .appendField("title", scenario.getTitle())
                .appendField("systemActor", scenario.getSystemActor())
                .appendField("actors", actorsJsonArray)
                .appendField("steps", nestedSteps);

        return new GsonBuilder()
                .setPrettyPrinting()
                .create()
                .toJson(json);
    }

    /**
     * Recursively creates a JSON array of steps for the chosen depth level.
     *
     * @param steps         the list of steps at the current level
     * @param currentLevel  the current depth level
     * @param maxLevel      the target depth level
     * @return              JSON array of steps for the chosen level
     */
    private JSONArray createRecursivelyNestedStepsJson(List<Step> steps, Integer currentLevel, Integer maxLevel) {
        JSONArray currentStepsArray = new JSONArray();
        steps.forEach(step ->
                currentStepsArray.add(new JSONObject()
                        .appendField("content", step.getContent())
                        .appendField("subSteps", currentLevel >= maxLevel ?
                                new JSONArray() :
                                createRecursivelyNestedStepsJson(step.getSubSteps(), currentLevel + 1, maxLevel)))
        );
        return currentStepsArray;
    }
}
