package org.example;
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class WordFrequencyCounter {
    public static void main(String[] args) {
        //sTOREING THE fILE pATH IN A VARIALBLE
        String MOFilePath = "C://Users/lenovo/Downloads/monisha/pageText.txt";
        int MOTopN = 5;

        try {
            // Step 1: Parse the text file to extract words
            var MOWords = parseMOWordsFromFile(MOFilePath);

            // Step 2: Use a hash table to count the occurrences of each word
            var MOWordCounts = countMOWordFrequencies(MOWords);

            // Step 3: Sort the words based on their frequency
            var MOSortedWordCounts = sortMOWordFrequencies(MOWordCounts);

            // Step 4: Display the top N most frequent words
            displayMOTopNWords(MOSortedWordCounts, MOTopN);

        } catch (IOException Merror) {
            System.err.println("Error has occuresd in this program for reading this file : " + Merror.getMessage());
        }
    }


    /**
     * This method counts the frequency of each word in a list.
     *
     * @param MOWords a list of words to count frequencies for
     * @return a map containing words and their corresponding frequencies
     */
    public static Map<String, Integer> countMOWordFrequencies(List<String> MOWords) {
        HashMap<String, Integer> MOWordCounts;
        MOWordCounts = new HashMap<String, Integer>();
        for (int i = 0, moWordsSize = MOWords.size(); i < moWordsSize; i++) {
            String MOWord = MOWords.get(i);
            MOWordCounts.put(MOWord, MOWordCounts.getOrDefault(MOWord, 0) + 1);
        }
        return MOWordCounts;
    }

    /**
     * This method sorts the word frequencies in descending order.
     *
     * @param MOWordCounts a map containing words and their corresponding frequencies
     * @return a list of entries sorted by frequency in descending order
     */
    public static List<Map.Entry<String, Integer>> sortMOWordFrequencies(Map<String, Integer> MOWordCounts) {
        ArrayList<Entry<String, Integer>> MOSortedWordCounts;
        MOSortedWordCounts = new ArrayList<Entry<String, Integer>>(MOWordCounts.entrySet());
        MOquickSort(MOSortedWordCounts, 0, MOSortedWordCounts.size() - 1);
        return MOSortedWordCounts;
    }

    /**
     * Quick Sort algorithm for sorting the word frequencies.
     */
    public static void MOquickSort(List<Map.Entry<String, Integer>> list, int low, int high) {
        if (low >= high) {
            return;
        }
        int pivotIndex;
        pivotIndex = Mopartition(list, low, high);
        MOquickSort(list, low, pivotIndex - 1);
        MOquickSort(list, pivotIndex + 1, high);
    }

    /**
     * Partition method for Quick Sort.
     */
    public static int Mopartition(List<Map.Entry<String, Integer>> list, int low, int high) {
        Entry<String, Integer> pivot = list.get(high);
        int Mi = low - 1;
        int Mj = low;
        if (Mj < high) {
            do {
                if (list.get(Mj).getValue() > pivot.getValue()) { // Sorting in descending order
                    Mi++;
                    Collections.swap(list, Mi, Mj);
                }
                Mj++;
            } while (Mj < high);
        }
        Collections.swap(list, Mi + 1, high);
        return Mi + 1;
    }

    /**
     * This method displays the top N words with their frequencies.
     *
     * @param MOSortedWordCounts a list of entries sorted by frequency
     * @param MOTopN the number of top words to display
     */
    public static void displayMOTopNWords(List<Map.Entry<String, Integer>> MOSortedWordCounts, int MOTopN) {
        int MOi = 0;
        while (MOi < MOTopN && MOi < MOSortedWordCounts.size()) {
            Entry<String, Integer> MOEntry = MOSortedWordCounts.get(MOi);
            System.out.printf("%s: %d%n", MOEntry.getKey(), MOEntry.getValue());
            MOi++;
        }
    }

    /**
     * This method reads a file and parses its content into a list of words.
     *
     * @param MOFilePath the path to the file to be read
     * @return a list of words extracted from the file
     * @throws IOException if an error occurs while reading the file
     */
    public static List<String> parseMOWordsFromFile(String MOFilePath) throws IOException {
        List<String> MOWords;
        MOWords = new ArrayList<>();
        try (var MOReader = new BufferedReader(new FileReader(MOFilePath))) {
            String MOLine;
            while ((MOLine = MOReader.readLine()) != null) {
                /* Split the given lines into words using non-word characters as delimiters */
                var MOWordArray = MOLine.split("\\W+");
                int moWordArrayLength = MOWordArray.length;
                for (int i = 0; i < moWordArrayLength; i++) {
                    var MOWord = MOWordArray[i];
                    if (!MOWord.isEmpty()) {
                        MOWords.add(MOWord.toLowerCase());
                    }
                }
            }
        }
        return MOWords;
    }
}
