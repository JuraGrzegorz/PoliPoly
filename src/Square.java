import javax.swing.*;
public class Square {

    private boolean isOwnable;
    private boolean isOwned;
    private String owner;

    private  String name;

    private  int position;

    private  int charge;
    public Square(String name, int position, int charge){
        this.name=name;
        this.position=position;
        this.charge=charge;
        this.name=null;

//        JButton[] field = new JButton[4];
//        for(int i=0; i<field.length; i++){
//            field[i] = new JButton();
//        }
    }

    public Square() {
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public int getCharge() {
        return charge;
    }

    public boolean getOwned() {
        return isOwned;
    }

    public String getOwner() {
        return owner;
    }

    public boolean getOwnable(){
        return isOwnable;
    }

    public void setOwned(boolean owned) {
        isOwned = owned;
    }

    public void setOwnable(boolean ownable) {
        isOwnable = ownable;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }


}
