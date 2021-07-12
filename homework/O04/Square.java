package comp1110.homework.O04;

public class Square extends Shape{

    public double side;
    public double length;
    public double x;
    public double y;

    Square(double length, double x, double y){
        this.length = length;
        this.x = x;
        this.y = y;
    }

    Square(double side){
        this.side = side;
    }

    @Override
    public double perimeter(){
        return side * 4.0;
    }

    @Override
    public double area(){
        return side * side;
    }

    @Override
    public boolean overlaps(Shape other){
        return true;
    }

}
