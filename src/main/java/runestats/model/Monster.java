package runestats.model;

import java.util.List;

public class Monster {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExamine() {
        return examine;
    }

    public void setExamine(String examine) {
        this.examine = examine;
    }

    public List<Item> getLoot() {
        return loot;
    }

    public void setLoot(List<Item> loot) {
        this.loot = loot;
    }

    private String name;
    private String examine;
    private List<Item> loot;


    public Monster(String name, String examine, List<Item> loot) {
        this.name = name;
        this.examine = examine;
        this.loot = loot;
    }
}
