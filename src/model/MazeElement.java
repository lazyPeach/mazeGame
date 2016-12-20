package model;

/**
 */
public class MazeElement {
    public enum Element {wall, path, robot, finish};

    public boolean visibility = true;
    public Element element;

    public MazeElement(char element) {
        if (element == ' ') this.element = Element.path;
        if (element == 'w') this.element = Element.wall;
        if (element == 'r') this.element = Element.robot;
        if (element == 'f') this.element = Element.finish;
    }
}
