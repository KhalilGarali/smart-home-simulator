package main.java.model.rooms;

public class Kitchen extends Room{

    private int width, height;

    public Kitchen(int width, int height){
        super();
        this.height = height;
        this.width = width;
    }

    public int getHeight(){
        return this.height;
    }

    public int getWidth(){
        return this.width;
    }

    @Override
    public String toString() {
        return "Kitchen" + super.toString();
    }
}
