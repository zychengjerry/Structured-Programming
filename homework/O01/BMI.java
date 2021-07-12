package comp1110.homework.O01;

import java.util.Scanner;

public class BMI {
    //public static class mainBMI{
        private String name;
        private double height;
        private double weight;
        //private double BMI;

        public BMI(String name, double height, double weight){
            this.name = name;
            this.height = height;
            this.weight = weight;
        }

        public double getBMI(){
            return weight/(height*height);
        }

        public static void main(String[] args) {
            Scanner in = new Scanner(System.in);
            String name1 = in.nextLine();
            double height1 = in.nextDouble();
            double weight1 = in.nextDouble();
            BMI p1 = new BMI(name1,height1,weight1);
            System.out.println(p1.toString());
        }

        @Override
        public String toString(){
            return name + " is " + height + "m tall and is " + weight + "Kg and has a BMI of " + getBMI() + "Kg/m^2";
        }
    }


//}
