package lovebabbarDSA.tries;

import java.util.*;

public class Problems {


    //Unique rows in boolean matrix

    // Non trie way
/*    public static ArrayList<ArrayList<Integer>> uniqueRow(int a[][], int r, int c)
    {
        HashSet<String> set = new HashSet<>();
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        for(int i = 0; i < r; i++) {
            ArrayList<Integer> list = new ArrayList<>();
            StringBuilder s = new StringBuilder();
            for(int j = 0; j < c; j++) {
                s.append(String.valueOf(a[i][j]));
                list.add(a[i][j]);
            }
            String ss = s. toString();
            if(!set.contains(ss)) {
                set.add(ss);
                result.add(list);
            }
        }
        return result;
    }*/


    //trie way :

    public static ArrayList<ArrayList<Integer>> uniqueRow(int a[][], int r, int c)
    {

        class TrieNode01 {
            boolean ends;
            TrieNode01[] children;
            TrieNode01() {
                this.ends = false;
                children = new TrieNode01[2];
            }
        }

        class Trie01 {
            TrieNode01 root;
            Trie01() {
                root = new TrieNode01();
            }

            // this method return true if string is not already present
            public boolean insert(String s) {
                TrieNode01 itr = root;
                boolean stringPresent = true;
                for(int i = 0; i < s.length(); i++) {
                    if(itr.children[s.charAt(i) - '0'] == null) {
                        itr.children[s.charAt(i) - '0'] = new TrieNode01();
                        stringPresent = false;
                    }
                    itr = itr.children[s.charAt(i) - '0'];
                }
                if(itr.ends == false) {
                    itr.ends = true;
                    stringPresent = false;
                }
                return !stringPresent;
            }
        }
        Trie01 trie = new Trie01();
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        for(int i = 0; i < r; i++) {
            ArrayList<Integer> list = new ArrayList<>();
            StringBuilder s = new StringBuilder();
            for(int j = 0; j < c; j++) {
                s.append(a[i][j]);
                list.add(a[i][j]);
            }
            String ss = s. toString();
            if(trie.insert(ss)) {
                result.add(list);
            }
        }
        return result;
    }


    //Print Anagrams Together


    // NOTE : can be done using hashmap also
    public List<List<String>> Anagrams(String[] string_list) {


        class TrieNode {
            char val;
            boolean ends;
            TrieNode[] children;
            List<String> words;
            TrieNode(char val) {
                this.val = val;
                this.ends = false;
                this.children = new TrieNode[26];
                words = new ArrayList<>();
            }
        }

        class Trie {
            TrieNode root;
            Trie() {
                root = new TrieNode('#');
            }

            public void insert(String s, String word) {
                TrieNode itr = root;
                for(int i = 0; i < s.length(); i++) {
                    if(itr.children[s.charAt(i) - 'a'] == null) {
                        itr.children[s.charAt(i) - 'a'] = new TrieNode(s.charAt(i));
                    }
                    itr = itr.children[s.charAt(i) - 'a'];
                }
                if(itr.ends == false){
                    itr.ends = true;
                }
                itr.words.add(word);
            }

            public void getAllWords(List<List<String>> result) {
                getAllWordsUtil(root, result);
            }

            public void getAllWordsUtil(TrieNode root, List<List<String>> result) {
                if(root == null)
                    return;
                if(root.ends == true) {
                    result.add(root.words);
                }
                for(int i = 0; i < 26; i++) {
                    if(root.children[i] != null)
                        getAllWordsUtil(root.children[i], result);
                }
            }
        }


        List<List<String>> result = new ArrayList<>();
        Trie trie = new Trie();

        for (String s : string_list) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String ss = new String(chars);
            trie.insert(ss, s);
        }

        trie.getAllWords(result);

        return result;
    }


    //Find shortest unique prefix for every word in a given list |

    static void findPrefixes(String arr[], int n) {
        class TrieNode {
            char val;
            TrieNode[] children;
            int frequency;
            TrieNode(char val) {
                this.val = val;
                this.children = new TrieNode[26];
                this.frequency = 1;
            }
        }

        class Trie {
            TrieNode root;
            Trie() {
                root = new TrieNode('#');
                root.frequency = 0;
            }

            public void insert(String s) {
                TrieNode itr = root;
                for(int i = 0; i < s.length(); i++) {
                    if(itr.children[s.charAt(i) - 'a'] == null) {
                        itr.children[s.charAt(i) - 'a'] = new TrieNode(s.charAt(i));
                    } else {
                        itr.children[s.charAt(i) - 'a'].frequency++;
                    }
                    itr = itr.children[s.charAt(i) - 'a'];
                }
            }

            public void printShortestPrefix() {
                List<Character> list = new ArrayList<>();
                printShortestPrefixUtil(root, list);
            }

            public void printShortestPrefixUtil(TrieNode root, List<Character> list) {
                if(root == null)
                    return;
                if(root.frequency == 1) {
                    for(Character c : list) {
                        System.out.print(c);
                    }
                    System.out.print(" ");
                    return;
                }
                for(int i = 0; i < 26; i++) {
                    if(root.children[i] != null) {
                        list.add((char) ('a' + i));
                        printShortestPrefixUtil(root.children[i], list);
                        list.remove(list.size() - 1);
                    }
                }
            }

        }

        Trie trie = new Trie();
        for(String s : arr) {
            trie.insert(s);
        }
        trie.printShortestPrefix();
    }



    //Phone directory

    // NOTE can also be done by storing strings in nodes but it will need more space

    static ArrayList<ArrayList<String>> displayContacts(int n, String contact[], String s)
    {
        class TrieNode{
            char val;
            TrieNode[] children;
            boolean ends;
            TrieNode(char val) {
                this.val = val;
                children = new TrieNode[26];
                this.ends = false;
            }
        }

        class Trie {
            TrieNode root;
            Trie() {
                root = new TrieNode('#');
            }
            void insert(String s) {
                TrieNode itr = root;
                for(int i = 0; i < s.length(); i++) {
                    if(itr.children[s.charAt(i) - 'a'] == null) {
                     itr.children[s.charAt(i) - 'a'] = new TrieNode(s.charAt(i));
                    }
                    itr = itr.children[s.charAt(i) - 'a'];
                }
                itr.ends = true;
            }

            void displayWords(String s, ArrayList<ArrayList<String>> result) {
                TrieNode prev = root;
                String prefix = "";
                int i = 0;
                for(;i < s.length(); i++) {
                    ArrayList<String> list = new ArrayList<>();
                    prefix = prefix + s.charAt(i);
                    if(prev.children[s.charAt(i) - 'a'] == null) {
                        break;
                    } else {
                        prev = prev.children[s.charAt(i) - 'a'];
                        displayWordsUtil(prev, prefix, list);
                    }
                    result.add(list);
                }
                for(;i < s.length(); i++) {
                    result.add(new ArrayList<>(Arrays.asList("0")));
                }
            }

            void displayWordsUtil(TrieNode root, String prefix, ArrayList<String> list) {
                if(root.ends) {
                    list.add(prefix);
                }
                for(int i = 0; i < 26; i++) {
                    if(root.children[i] != null) {
                        displayWordsUtil(root.children[i], prefix + root.children[i].val, list);
                    }
                }
            }
        }

        ArrayList<ArrayList<String>> result = new ArrayList<>();
        Trie trie = new Trie();
        for(String string : contact) {
            trie.insert(string);
        }
        trie.displayWords(s, result);
        return result;
    }

    public static void main(String[] args) {
        String arr[]  =  {"geeksgeeks", "geeksquiz", "geeksforgeeks"};
        int n = arr.length;
        findPrefixes(arr, n);
    }
}
