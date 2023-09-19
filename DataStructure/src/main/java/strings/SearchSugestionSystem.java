package strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchSugestionSystem {

    //1268. Search Suggestions System

    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        Arrays.sort(products);
        List<List<String>> result = new ArrayList<>();
        Trie trie = new Trie();
        for(int i = 0; i < products.length; i++)
            trie.insert(products[i]);

        for(int i = 1; i <= searchWord.length(); i++) {
            String prefix = searchWord.substring(0, i);
            List<String> prefixSearch = trie.searchPrefix(prefix);
            if(prefixSearch == null) {
                result.add(new ArrayList<>());
            } else {
                result.add(new ArrayList<>(prefixSearch));
            }
        }
        return result;
    }

    class TrieNode {
        char ch;
        TrieNode[] children;
        boolean ends;
        List<String> prefixSearch;
        TrieNode(char c) {
            this.ch = c;
            children = new TrieNode[26];
            prefixSearch = new ArrayList<>();
            ends = false;
        }
    }

    class Trie {
        TrieNode root;
        Trie() {
            root = new TrieNode('#');
        }

        public void insert(String word) {
            insertUtil(word, 0, root);
        }

        private void insertUtil(String word, int index, TrieNode itr) {
            if(itr != root) {
                if(itr.prefixSearch.size() < 3) {
                    itr.prefixSearch.add(word);
                }
            }
            if(index == word.length()) {
                itr.ends = true;
                return;
            }
            if(itr.children[word.charAt(index) - 'a'] == null) {
                itr.children[word.charAt(index) - 'a'] = new TrieNode(word.charAt(index));
            }
            insertUtil(word, index + 1, itr.children[word.charAt(index) - 'a']);
        }

        public List<String> searchPrefix(String prefix) {
            TrieNode itr = root;
            int index = 0;
            while (index < prefix.length()) {
                if(itr.children[prefix.charAt(index) - 'a'] == null) {
                    return null;
                }
                itr = itr.children[prefix.charAt(index) - 'a'];
                index++;
            }
            return itr.prefixSearch;
        }
    }

}
