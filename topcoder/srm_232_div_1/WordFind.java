public class WordFind {
    public static class Node {
        private int x;
        private int y;
        private Node[] childs;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
            childs = null;
        }
    }

    public static class Trie {
        private Node[] childs;

        public Trie() {
            childs = new Node[26];
        }

        public void insert(String word, int x, int y) {
            Node cur = null;
            for (int i = 0; i < word.length(); i++) {
                if (cur == null) {
                    // this is root
                    if (childs[word.charAt(i) - 'A'] == null) {
                        childs[word.charAt(i) - 'A'] = new Node(x, y);
                    }
                    cur = childs[word.charAt(i) - 'A'];
                } else {
                    // this is childs
                    if (cur.childs == null) {
                        cur.childs = new Node[26];
                    }
                    if (cur.childs[word.charAt(i) - 'A'] == null) {
                        cur.childs[word.charAt(i) - 'A'] = new Node(x, y);
                    }
                    cur = cur.childs[word.charAt(i) - 'A'];
                }
            }
        }

        public String query(String word) {
            Node cur = null;
            for (int i = 0; i < word.length(); i++) {
                if (cur == null) {
                    if (childs[word.charAt(i) - 'A'] == null) {
                        return "";
                    }
                    cur = childs[word.charAt(i) - 'A'];
                } else {
                    if (cur.childs == null || cur.childs[word.charAt(i) - 'A'] == null) {
                        return "";
                    }
                    cur = cur.childs[word.charAt(i) - 'A'];
                }
            }
            return "" + cur.y + " " + cur.x;
        }
    }

    public String[] findWords(String[] grid, String[] query) {
        // init grid
        Trie trie = new Trie();
        for (int i = 0; i < grid[0].length(); i++) {
            for (int j = 0; j < grid.length; j++) {
                String word;

                // insert right
                word = "";
                for (int k = i; k < grid[0].length(); k++) {
                    word = word + grid[j].charAt(k);
                }
                trie.insert(word, i, j);

                // insert down
                word = "";
                for (int k = j; k < grid.length; k++) {
                    word = word + grid[k].charAt(i);
                }
                trie.insert(word, i, j);

                // insert right-down
                word = "";
                for (int k = 0; k < Math.min(grid[0].length() - i, grid.length - j); k++) {
                    word = word + grid[j + k].charAt(i + k);
                }
                trie.insert(word, i, j);
            }
        }

        // check queries
        String[] results = new String[query.length];
        for (int i = 0; i < query.length; i++) {
            String word = query[i];
            if (word.length() > Math.max(grid[0].length(), grid.length)) {
                results[i] = "";
            } else {
                results[i] = trie.query(word);
            }
        }

        return results;
    }

    public static void main(String[] args) {
        WordFind wf = new WordFind();
        String[] grid = {"EASYTOFINDEAGSRVHOTCJYG", "FLVENKDHCESOXXXXFAGJKEO", "YHEDYNAIRQGIZECGXQLKDBI", "DEIJFKABAQSIHSNDLOMYJIN", "CKXINIMMNGRNSNRGIWQLWOG", "VOFQDROQGCWDKOUYRAFUCDO", "PFLXWTYKOITSURQJGEGSPGG"};
        String[] query = {"EASYTOFIND", "DIAG", "GOING", "THISISTOOLONGTOFITINTHISPUZZLE"};
        String[] result = wf.findWords(grid, query);
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
    }
}