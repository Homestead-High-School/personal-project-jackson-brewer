import java.util.*;

public class AnagramSolver {

    public ArrayList<String> anagram = new ArrayList<String>();
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
        String stri = s;
        s = s.replaceAll(" ", "");
        s = s.toLowerCase();
        for(int i = 0; i < s.length(); i++){ // need to impliment a case where the imput isnt a letter or number *******
            phrase.add(s.charAt(i));
        }
        set(s); // sets the letter inventory 
        if(phrase.size() == 0){
            return;
        }
        ArrayList<String> newDict = new ArrayList<String>(); // updated dictionary with the correct size words
        for(int i = 0; i < dict.size(); i++){
            if(phrase.size() >= dict.get(i).length()){
                newDict.add(dict.get(i)); // add good word to the new dicitonary
            }
        }
        boolean go = true;
        while(go){
            ArrayList <String> st = new ArrayList<>();
            go=false;
            for(String x: newDict){ // loop through all the words in the new dictionary 
                if(recursion(s, x)){ // if the word in the dictionary can go into the phrase
                    anagram.add(x);
                    st.add(x);
                    go=true;
                    for(int i = 0; i < x.length(); i++){
                        subtract(x.charAt(i));
                        s = remove(s, x.charAt(i));
                    }
                }
            }
            for(int i = 0; i < st.size(); i++){
                newDict.remove(newDict.indexOf(st.get(i)));
            }
            s = stri;
            String anagramTotal = "";
            //if anagram variable to keep track of the size) = stri.length();
            for(int l = 0; l < anagram.size(); l++){
                anagramTotal += anagram.get(l);
            stri = stri.replace(" ", "");
            }
            if(anagramTotal.length() == stri.length()){
                printAnagram(anagram);
            }
            anagramTotal = "";      
            anagram.clear();
        }

    }

    public boolean recursion(String s, String d){
        for(int i = 0; i < d.length(); i++){
            if(s.indexOf(d.charAt(i))>=0){
                letterInventory.put(s.charAt(i), letterInventory.get(d.charAt(i))-1);
               s = remove(s , d.charAt(i));
               d = remove(d, d.charAt(i));
                i--;
                if(d.equals("")){
                    return true;
                }
            }
            else{
                return false;
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

    public String remove(String s, char x){
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == x && s.indexOf(x)==0){ // if the matching letter is the first in the phrase
                s = s.substring(1);
                return s;
            }
            else if(s.charAt(i) == x && i > 0 && i < s.length()-1){
                s = s.substring(0,i) + s.substring(i+1); 
                return s;
            }
            else if(s.charAt(i) == x && i == s.length()-1){
                s = s.substring(0,i);
            }
        }
    return s;
    }

    public void subtract(char c){
        int x = letterInventory.get(c);
        letterInventory.put(c, x--);
    }

    public void printAnagram(ArrayList <String> a){
        for(int i = 0; i < a.size(); i++){{
            a.add(0, a.get(a.size()-1));
            a.remove(a.size()-1);
            System.out.println(a);
        }
    }
    }
}


  
