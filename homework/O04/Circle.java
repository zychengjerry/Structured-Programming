package comp1110.homework.O04;

public class Circle extends Shape{
    public double radius;
    public double length;
    public double x;
    public double y;

    Circle(double length, double x, double y){
        this.length = length;
        this.x = x;
        this.y = y;
    }

    Circle(double radius){
        this.radius = radius;
    }

    @Override
    public double perimeter(){
        return radius * Math.PI * 2;
    }

    @Override
    public double area(){
        return Math.PI * radius * radius;
    }

    @Override
    public boolean overlaps(Shape other){
        return true;
    }

}
