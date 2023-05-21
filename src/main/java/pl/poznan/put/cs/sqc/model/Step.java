package pl.poznan.put.cs.sqc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Step {
    private String content;
    List<Step> subSteps;
}