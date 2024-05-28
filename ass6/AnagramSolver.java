import java.util.*;

public class AnagramSolver {

    private ArrayList<String> dict = new ArrayList<String>();
    private ArrayList<Character> phrase = new ArrayList<Character>();
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
            phrase.add(s.charAt(i));
        }
        System.out.println(phrase);
        set(s); // sete the letter inventory 
        if(phrase.size() == 0){
            return;
        }
        ArrayList<String> newDict = new ArrayList<String>(); // updated dictionary with the correct size words
        for(int i = 0; i < dict.size(); i++){
            if(phrase.size() >= dict.get(i).length()){
                newDict.add(dict.get(i)); // add good word to the new dicitonary
            }
        }
        System.out.println(letterInventory);
        for(String x: newDict){ // loop through all the words in the new dictionary 
            if(recursion(s, x)){ // if the word in the dictionary can go into the phrase
                //s = remove(s,x);
                s = remove(s,x);
                //remove the dictionary word from the variable s
                for(int i = 0; i < x.length(); i++){
                    subtract(x.charAt(i));
                    phrase.remove(phrase.indexOf(x.charAt(i)));
                }
                // s.remove x
                //need to update the variable s
                System.out.println(phrase);
            }
        }
    }

    public boolean recursion(String s, String d){
        // need a for loop to go through the dictionary word and if it contains all the letters, it removes it
        // right now I just remove if the letters are in the exact order of the dictionary word
        /* 
        for(int i = 0; i < d.length(); i++){
            for(int j = 0; j < s.length(); j++){
                if(d.charAt(i) == (s.charAt(j))){
        */
        // letterInventory.put(d.charAt(i), letterInventory.get(d.charAt(i))-1);
        int x = 0;
            if(s.charAt(x) == (d.charAt(x))){
                letterInventory.put(s.charAt(x), letterInventory.get(d.charAt(x))-1);
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

    public String remove(String s, String x){
        for(int i = 0; i < s.length(); i++){
            for(int j = 0; j < x.length(); j++){
                if(s.charAt(i)==x.charAt(j))
                //remove the char at i from the variable s
            }
        }
        System.out.println(s);
        return s;
    }

    public void subtract(char c){
        int x = letterInventory.get(c);
        letterInventory.put(c, x--);
    }

}


  
