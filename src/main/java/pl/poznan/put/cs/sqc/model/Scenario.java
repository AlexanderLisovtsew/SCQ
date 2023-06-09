package pl.poznan.put.cs.sqc.model;

import lombok.Data;

import java.util.List;

/**
 * Author: Aliaksandr Lisoutau
 */
@Data
public class Scenario {
    private String title;
    private String systemActor;
    private List<Step> steps;
    private List<String> actors;
}