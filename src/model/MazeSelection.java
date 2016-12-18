package model;

import java.util.HashMap;
import java.util.Set;

enum Difficulty {Easy, Medium, Hard}

public class MazeSelection {
    private String mazeName;
    private Difficulty difficulty;
    private HashMap<String, Difficulty> difficultyMap;

    public MazeSelection() {
        difficultyMap = new HashMap<>();
        difficultyMap.put("Easy", Difficulty.Easy);
        difficultyMap.put("Medium", Difficulty.Medium);
        difficultyMap.put("Hard", Difficulty.Hard);
    }

    //todo
    public String[] getMazes() {
        return null;
    }

    //todo
    public String[] getDificulties() {
        Set<String> keys = difficultyMap.keySet();
        String[] difficulties = keys.toArray(new String[keys.size()]);
        return difficulties;
    }

    //todo
    public String getSelectedMaze() {
        return "";
    }

    //// TODO: 12/18/16
    public Difficulty getSelectedDifficulty() {
        return Difficulty.Easy;
    }

    public void setMazeName(String mazeName) {
        this.mazeName = mazeName;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficultyMap.get(difficulty);
    }



}
