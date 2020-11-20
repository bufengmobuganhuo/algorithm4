package leetcode.string;

import org.omg.CORBA.INTERNAL;

import java.util.*;

/**
 * @author yuzhang
 * @date 2020/11/5 4:14 下午
 * TODO
 */
public class Ex126_1 {
    public static void main(String[] args) {
        List<String> wordList=Arrays.asList("hot","dot","dog","lot","log","cog");
        String beginWord="hit";
        String endWord="cog";
        Ex126_1 ex126_1 = new Ex126_1();
        System.out.println(ex126_1.findLadders(beginWord,endWord,wordList));
    }
    private Map<String,Integer> word2Idx;
    private List<String> idx2Word;
    private List<Integer>[] adj;

    public Ex126_1() {
        word2Idx = new HashMap<>();
        idx2Word = new ArrayList<>();
    }

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        for (int i = 0; i < wordList.size(); i++) {
            word2Idx.put(wordList.get(i),i);
            idx2Word.add(wordList.get(i));
        }
        List<List<String>> ans = new ArrayList<>();
        if (!word2Idx.containsKey(endWord)){
            return ans;
        }
        if (!word2Idx.containsKey(beginWord)){
            idx2Word.add(beginWord);
            word2Idx.put(beginWord,idx2Word.size()-1);
        }
        buildGraph();
        bfs(ans,word2Idx.get(beginWord),word2Idx.get(endWord));
        return ans;
    }

    private void bfs(List<List<String>> ans,int start,int target){
        Queue<List<Integer>> que = new LinkedList<>();
        List<Integer> path = new ArrayList<>();
        int[] distTo = new int[adj.length];
        Arrays.fill(distTo,Integer.MAX_VALUE);
        path.add(start);
        que.offer(path);
        distTo[start]=0;
        while (!que.isEmpty()){
            List<Integer> nowPath = que.poll();
            Integer lastVertex = nowPath.get(nowPath.size()-1);
            if (lastVertex == target){
                List<String> tmp = new ArrayList<>();
                for (int id : nowPath) {
                    tmp.add(idx2Word.get(id));
                }
                ans.add(tmp);
            }else{
                for (int adjVertex : adj[lastVertex]) {
                    if (distTo[lastVertex]+1<=distTo[adjVertex]){
                        distTo[adjVertex]=distTo[lastVertex]+1;
                        List<Integer> tmp = new ArrayList<>(nowPath);
                        tmp.add(adjVertex);
                        que.offer(tmp);
                    }
                }
            }
        }
    }

    private void buildGraph(){
        adj = new List[word2Idx.size()];
        for (int i = 0; i < adj.length; i++) {
            adj[i] = new LinkedList<>();
        }
        for (int i = 0; i < idx2Word.size(); i++) {
            for (int j = i+1; j < idx2Word.size(); j++) {
                if (transformCheck(idx2Word.get(i),idx2Word.get(j))){
                    adj[i].add(j);
                    adj[j].add(i);
                }
            }
        }
    }

    private boolean transformCheck(String word1, String word2){
        int diff = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i)!=word2.charAt(i)){
                diff++;
            }
            if (diff>1){
                return false;
            }
        }
        return true;
    }
}
