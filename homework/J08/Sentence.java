package comp1110.homework.J08;


public class Sentence {
    public Word[] words;

    Sentence(int size){
        this.words = new Word[size];
    }

    public boolean isValid(){
        if (words.length<1){
            return false;
        }else {
            return words[words.length - 1].type == Word.Type.NOUN;
        }
    }

    public static class Word{
        String value;
        Type type;

        public enum Type{
            NOUN, ADJECTIVE, VERB
        }

        Word(String value, Type type){
            this.value = value;
            this.type = type;

        }
    }
}
