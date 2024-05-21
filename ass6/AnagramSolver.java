import java.util.*;

public class AnagramSolver {

    private ArrayList<String> dict = new ArrayList<String>();
    private ArrayList<String> phrase = new ArrayList<String>();
    private HashMap<Character, Integer> letterInventory = new HashMap<>(); //hash map to store the letters and the count 

    //constructs an anagram solver that will use the given list as its dictionary
    AnagramSolver(List<String> list){
        for(int i = 0; i < list.size(); i++){ 
            dict.add(list.get(i)); // adds all the words in the dictionary into the list 
        }
    } 

    //use recursive backtracking to find combinations of words that have the same letters as the given string
    void print(String s, int max){
        s = s.replaceAll(" ", "");
        for(int i = 0; i < s.length(); i++){ // need to impliment a case where the imput isnt a letter or number *******
            phrase.add(s.substring(i,i+1));
        }
        set(s);
        if(phrase.size() == 0){
            return;
        }
        ArrayList<String> newDict = new ArrayList<String>();
        for(int i = 0; i < dict.size(); i++){
            if(phrase.size() >= dict.get(i).length()){
                newDict.add(dict.get(i));
            }
        }
        dict = newDict;
        for(String x: dict){
            recursion(s, x);
        }
    }

    public boolean recursion(String s, String d){
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == (d.charAt(i))){
                s = s.substring(i);
                d = d.substring(i);
            }
        }
        return true;
    }

    public void set(String phrase) { // this method sets the letter inventory to keep track of how many of each letter we have in the hash map
        for(int i = 0; i < phrase.length(); i++){
            char letter = phrase.charAt(i);
            if(letterInventory.containsKey(letter)){
                int x = letterInventory.get(letter)+1; //increments the value in the hashmap
                letterInventory.put(letter, x);
            } else {
                letterInventory.put(letter, 1); // adds the new letter to the map
            }
        }
    }

    public void subtract(char c){
        int x = letterInventory.get(c);
        letterInventory.put(c, x--);
    }


}


  
