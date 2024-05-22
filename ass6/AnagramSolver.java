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
        String word = "";
        dict = newDict;
        for(String x: dict){
            if(recursion(s, x)){
                word += x;
                System.out.println(word);
            }
        }
        for(int j = 0; j < word.length(); j++){
            phrase.remove(word.charAt(j));
        }
        System.out.println(phrase);
    }

    public boolean recursion(String s, String d){
        int x = 0;
        // need a for loop to go through the dictionary word and if it contains all the letters, it removes it
        // right now I just remove if the letters are in the exact order of the dictionary word
            if(s.charAt(x) == (d.charAt(x))){
                x++;
                s = s.substring(x);
                d = d.substring(x);
                if(d.equals("")){
                    return true;
                }
                recursion(s, d);
            } else { 
                return false;
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


  
