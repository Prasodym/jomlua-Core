package de.jomlua.commands.Secure;

import java.util.ArrayList;
import java.util.List;

public enum banTimeUnit {
    SECOUND("Sekunde(n)", 1, "sec"),
    MINUTE("Minute(n)",60,"min"),
    HOUR("Stunde(n)", 3600,"hour"),
    DAY("Tag(e)", 86400, "day"),
    WEEK("Woche(n)", 604800, "week");

    private String name;
    private int seconds;
    private String shortcut;

    private banTimeUnit(String name, int seconds, String shortcut){
        this.name = name;
        this.seconds = seconds;
        this.shortcut = shortcut;
    }

    public int getSeconds() {
        return seconds;
    }

    public String getName() {
        return name;
    }

    public String getShortcut() {
        return shortcut;
    }

    public static List<String> getTime(){
        List<String> units = new ArrayList<String>();

        for (banTimeUnit unit: banTimeUnit.values()){
            units.add(unit.getShortcut().toLowerCase());
        }
        return units;
    }
    public static banTimeUnit getUnit(String unit){
        for (banTimeUnit units: banTimeUnit.values()){
            if (units.getShortcut().toLowerCase().equals(unit.toLowerCase())) {
                return units;
            }
        }
        return null;
    }
}
