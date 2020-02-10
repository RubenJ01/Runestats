package runestats.model;

public class Item {

    private String name;
    private String examine;

    public Item(String name, String examine, int rarity) {
        this.name = name;
        this.examine = examine;
        this.rarity = rarity;
    }

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

    public int getRarity() {
        return rarity;
    }

    public void setRarity(int rarity) {
        this.rarity = rarity;
    }

    private int rarity;

}
