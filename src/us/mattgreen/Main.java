package us.mattgreen;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Main {

    private final static FileInput indata = new FileInput("the_book.csv");
    private final static Map<String, Integer> map = new HashMap<String, Integer>();

    
    
    public static void main(String[] args) {
        new Main();
    }
    
    public Main() {
        String line;
        String[] words;
        // variable to house count of words with one occurrence
        int oneCountWords = 0;
        // number of words to display for final count
        final int NUMBER_OF_WORDS_RANKED = 20;

        while ((line = indata.fileReadLine()) != null) {
            // Remove anything that's not a letter or space
            line = line.replaceAll("[^a-zA-Z ]","")
                    .toLowerCase().trim();
           
            // Don't process lines with no characters
            if (line.isEmpty()) {
                continue;
            }
            
            // Split string over one or more spaces
            words = line.split(" +");
            
            // Look for each word in the map
            for (String word : words) {
                // This word isn't yet a key, init count to 1
                if (!map.containsKey(word)) {
                    map.put(word, 1);
                    oneCountWords++;
                }
                else {
                    // Increment count using word as key
                    map.put(word, map.get(word) + 1);
                    if (map.get(word) == 2) {
                        oneCountWords--;
                    }
                }

            }

            // Loop over entries in the map, getting each key/value pair
            // for (Map.Entry<String, Integer> entry : map.entrySet()) {
            //    System.out.println(entry.getKey() + " " + entry.getValue());
            //for (LinkedList<Map.Entry<String, Integer>> entry : list.size()) {
            //    System.out.println(entry.getKey() + " " + entry.getValue());

            }
            // Logic to sort map into a linked list
            LinkedList<Map.Entry<String, Integer>> list = new LinkedList<>(map.entrySet());
            Comparator<Map.Entry<String, Integer>> comparator = Comparator.comparing(Map.Entry::getValue);
            Collections.sort(list, comparator.reversed());
            
            // Print out most common words and their counts
            System.out.println("The " + NUMBER_OF_WORDS_RANKED + " most common words are: ");
            System.out.println("Rank\t" + "Word\t\t" + "Occurrences");
            
            int i;
            for (i = 0; i < NUMBER_OF_WORDS_RANKED; i++) {
                Map.Entry<String, Integer> item = list.get(i);
                System.out.println((i+1) + "\t" + item.getKey() + "\t\t" + item.getValue());
            }
            
            // display count of one-letter words
            System.out.println("There are " + oneCountWords + " words that appear only once.");
        }
    }
