import javax.swing.*;

public class GameButton extends JButton {
    private int id;
    private String name;
    public GameButton(int Id, String name){
        this.id = Id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
