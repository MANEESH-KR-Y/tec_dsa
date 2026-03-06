import java.util.*;

class TrieNode {
    Map<Character, TrieNode> children = new HashMap<>();
    int frequency = 0;
    boolean isWord = false;
}

class AutocompleteSystem {

    TrieNode root = new TrieNode();

    public void insert(String word) {
        TrieNode node = root;

        for(char c : word.toCharArray()) {
            node.children.putIfAbsent(c, new TrieNode());
            node = node.children.get(c);
        }

        node.isWord = true;
        node.frequency++;
    }

    public List<String> getTop5(String prefix) {
        TrieNode node = root;

        for(char c : prefix.toCharArray()) {
            if(!node.children.containsKey(c))
                return new ArrayList<>();
            node = node.children.get(c);
        }

        PriorityQueue<String> pq =
                new PriorityQueue<>((a,b) -> b.compareTo(a));

        dfs(node, prefix, pq);

        List<String> res = new ArrayList<>();
        int count = 0;

        while(!pq.isEmpty() && count < 5) {
            res.add(pq.poll());
            count++;
        }

        return res;
    }

    private void dfs(TrieNode node, String word, PriorityQueue<String> pq) {
        if(node.isWord)
            pq.offer(word);

        for(char c : node.children.keySet())
            dfs(node.children.get(c), word + c, pq);
    }
}
