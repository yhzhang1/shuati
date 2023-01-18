class Solution {
    public String alienOrder(String[] words) {
        if(words == null || words.length == 0) return "";
        Map<Character, Set<Character>> graph = new HashMap<>();
        int[] indegree = new int[26];
        buildGraph(words, graph, indegree);
        return bfs(graph, indegree);
    }
    private void buildGraph(String[] words, Map<Character, Set<Character>> graph, int[] indegree){
        for(String word : words){
            for(char c : word.toCharArray()){
                graph.putIfAbsent(c, new HashSet<>());
            }
        }
        for(int i = 1; i < words.length; i++){
            String first = words[i-1];
            String second = words[i];
            int len = Math.min(first.length(), second.length());
            for(int j = 0; j < len; j++){
                char key = first.charAt(j);
                char value = second.charAt(j);
                if(key != value){
                    if(!graph.get(key).contains(value)){
                        graph.get(key).add(value);
                        indegree[value - 'a']++;
                    }
                    break;
                }
            }
        }
    }
    
    private String bfs(Map<Character, Set<Character>> graph, int[] indegree){
        StringBuilder sb = new StringBuilder();
        Queue<Character> q = new LinkedList<>();
        for(char c: graph.keySet()){
            if(indegree[c - 'a'] == 0){
                q.offer(c);
                sb.append(c);
            }
        }
        
        while(!q.isEmpty()){
            char c = q.poll();
            for(char nextC : graph.get(c)){
                indegree[nextC - 'a']--;
                if(indegree[nextC - 'a'] == 0){
                    q.offer(nextC);
                    sb.append(nextC);
                }
            }
        }
        
        return sb.length() == graph.size() ? sb.toString() : "";
    }
}