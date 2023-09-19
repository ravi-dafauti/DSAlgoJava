package strings;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordSearch {



    // 79. Word Search


    // with visited array

/*    boolean[][] visited;
    public boolean exist(char[][] board, String word) {
        visited = new boolean[board.length][board[0].length];
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                if(check(board, word, 0, i, j) == true) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean check(char[][] board, String word, int index, int x, int y) {
        if(index == word.length()) {
            return true;
        }
        if(x < 0 || x >= board.length || y < 0 || y >= board[x].length)
            return false;

        if(visited[x][y] == false && board[x][y] == word.charAt(index)) {

            visited[x][y] = true;
            if(check(board, word, index + 1, x + 1, y) == true) {
                return true;
            }
            if(check(board, word, index + 1, x, y + 1) == true) {
                return true;
            }
            if( check(board, word, index + 1, x - 1, y) == true) {
                return true;
            }
            if(check(board, word, index + 1, x , y - 1) == true) {
                return true;
            }
            visited[x][y] = false;
        }
        return false;
    }*/

    // without visited

    public boolean exist(char[][] board, String word) {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                if(board[i][j] == word.charAt(0) && check(board, word, 0, i, j) == true) {
                    return true;
                }
            }
        }
        return false;
    }

    private int[] delta_r = {-1, 1, 0, 0};
    private int[] delta_c = {0, 0, 1, -1};

    public boolean check(char[][] board, String word, int index, int x, int y) {
        if(index == word.length()) {
            return true;
        }
        if(x < 0 || x >= board.length || y < 0 || y >= board[0].length)
            return false;
        if(board[x][y] == '$' || board[x][y] != word.charAt(index))
            return false;
        char ch = board[x][y];
        board[x][y] = '$';
        for(int k = 0; k < 4; k++) {
            if(check(board, word, index + 1, x + delta_r[k], y + delta_c[k]))
                return true;
        }
        board[x][y] = ch;
        return false;
    }

    // 212. Word Search II

    class Solution {

        class TrieNode {
            char a;
            boolean ends;
            TrieNode[] children;
            String word;
            TrieNode(char a) {
                this.a = a;
                this.ends = false;
                this.children = new TrieNode[26];
                this.word = null;
            }
        }
        class Trie {
            TrieNode root;
            Trie() {
                root = new TrieNode('#');
            }
            void insert(String word) {
                TrieNode itr = root;
                int pos = 0;
                while (pos < word.length()) {
                    if(itr.children[word.charAt(pos) - 'a'] == null) {
                        itr.children[word.charAt(pos) - 'a'] = new TrieNode(word.charAt(pos));
                    }
                    itr = itr.children[word.charAt(pos) - 'a'];
                    pos++;
                }
                if(itr.ends == false) {
                    itr.ends = true;
                    itr.word = word;
                }
            }
        }

        public List<String> findWords(char[][] board, String[] words) {
            Trie trie = new Trie();
            List<String> result = new ArrayList<>();
            for(String word : words) {
                trie.insert(word);
            }
            for(int i = 0; i < board.length; i++) {
                for(int j = 0; j < board[0].length; j++) {
                    dfs(board, i, j, result, trie.root);
                }
            }
            return result;
        }

        private int[] delta_row = {-1, 1, 0, 0};
        private int[] delta_col = {0, 0, 1, -1};

        public void dfs(char[][] board, int i, int j, List<String> result, TrieNode itr) {
            if(i < 0 || j < 0 || i >= board.length || j >= board[0].length)
                return;
            if(board[i][j] == '$' || itr.children[board[i][j] - 'a'] == null)
                return;
            itr = itr.children[board[i][j] - 'a'];
            if(itr.word != null) {
                result.add(itr.word);
                itr.word = null;
            }
            char ch = board[i][j];
            board[i][j] = '$';

            for(int k = 0 ; k < 4; k++) {
                dfs(board, i + delta_row[k], j + delta_col[k], result, itr);
            }
            board[i][j] = ch;
        }
    }
}
