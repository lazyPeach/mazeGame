package model;

import java.nio.file.*;
import java.util.HashMap;
import java.util.Set;

enum Difficulty {Easy, Medium, Hard}

public class MazeSelection {
    private String mazeName;
    private Difficulty difficulty;
    private HashMap<String, Difficulty> difficultyMap;
    private HashMap<String, String> mazeMap;

    public MazeSelection() {
        createDifficultiesMap();
        createMazesMap();

        // set default values
        difficulty = Difficulty.Easy;
        mazeName = getMazes()[0];
    }

    private void createDifficultiesMap() {
        difficultyMap = new HashMap<>();
        difficultyMap.put("Easy", Difficulty.Easy);
        difficultyMap.put("Medium", Difficulty.Medium);
        difficultyMap.put("Hard", Difficulty.Hard);
    }

    private void createMazesMap() {
        mazeMap = new HashMap<>();

        Path dir = Paths.get("./filesResource/maze");
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, "*.txt")) {
            for (Path entry: stream) {
                String path = entry.toString();
                String[] splits = path.split("/");
                String mazeName = splits[splits.length - 1];
                mazeName = mazeName.substring(0, mazeName.length() - 4);

                mazeMap.put(mazeName, path);
            }
        } catch (Exception ex) {
            System.out.println("Exception caught while iterating through mazes");
        }
    }

    public String[] getMazes() {
        Set<String> keys = mazeMap.keySet();
        String[] mazes = keys.toArray(new String[keys.size()]);
        return mazes;
    }

    public String[] getDificulties() {
        Set<String> keys = difficultyMap.keySet();
        String[] difficulties = keys.toArray(new String[keys.size()]);
        return difficulties;
    }

    public String getMazeName() {
        return mazeName;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setMazeName(String mazeName) {
        this.mazeName = mazeName;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficultyMap.get(difficulty);
    }



}
