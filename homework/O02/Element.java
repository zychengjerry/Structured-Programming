package comp1110.homework.O02;

public enum Element {
    H(1,1,1),
    He(2,18,1),
    Li(3,1,2),
    Be(4,2,2),
    B(5,13,2);

    //final private char weight;

    private int weight=0;
    private int group=0;
    private int period=0;
    private int[] facts;


    Element(int weight, int group, int period){
        this.facts = new int[]{weight,group,period};
    }

    public int[] getFacts(){
        return facts;
    }

}
