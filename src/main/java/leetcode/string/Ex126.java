package leetcode.string;

import java.util.*;

/**
 * @author yuzhang
 * @date 2020/8/24 10:53 上午
 * TODO
 */
public class Ex126 {
    public static void main(String[] args) {
        List<String> wordList=Arrays.asList("hot","dot","dog","lot","log","cog");
        String beginWord="hit";
        String endWord="cog";
        Ex126 ex126=new Ex126();
        ex126.findLadders(beginWord,endWord,wordList);
    }
    /**
     * 通过单词获取到id
     */
    private Map<String,Integer> wordToIdx;
    /**
     * 通过id获取到单词
     */
    private List<String> idxToWord;
    private List<List<String>> ans;

    public Ex126() {
        wordToIdx=new HashMap<>();
        idxToWord=new ArrayList<>();
    }

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        int id=0;
        for (; id < wordList.size(); id++) {
            wordToIdx.put(wordList.get(id),id);
            idxToWord.add(wordList.get(id));
        }
        // 如果词典中不包含结束单词，则无法转换
        if (!wordToIdx.containsKey(endWord)){
            return new ArrayList<>();
        }
        if (!wordToIdx.containsKey(beginWord)){
            wordToIdx.put(beginWord,id);
            idxToWord.add(beginWord);
        }
        ans=new ArrayList<>();
        // 构造无向图
        List<Integer>[] adj=buildGraph(idxToWord);
        bfs(adj,idxToWord,wordToIdx.get(beginWord),wordToIdx.get(endWord));

        return ans;
    }

    private void bfs(List<Integer>[] adj,List<String> idToWord,int startVertex,int target){
        Queue<List<Integer>> queue=new LinkedList<>();
        List<Integer> beginPath=new ArrayList<>();
        beginPath.add(startVertex);
        queue.offer(beginPath);
        int[] distTo=new int[idToWord.size()];
        Arrays.fill(distTo,Integer.MAX_VALUE);
        distTo[startVertex]=0;

        while (!queue.isEmpty()){
            List<Integer> nowPath=queue.poll();
            int lastVertex=nowPath.get(nowPath.size()-1);
            if (lastVertex==target){
                List<String> tmp=new ArrayList<>();
                for (Integer vertex : nowPath) {
                    tmp.add(idToWord.get(vertex));
                }
                ans.add(tmp);
            }else{
                for (int adjVertex : adj[lastVertex]) {
                    if (distTo[lastVertex]+1<=distTo[adjVertex]){
                        distTo[adjVertex]=distTo[lastVertex]+1;
                        List<Integer> tmp=new ArrayList<>(nowPath);
                        tmp.add(adjVertex);
                        queue.offer(tmp);
                    }
                }
            }
        }
    }

    private List<Integer>[] buildGraph(List<String> idToWord){
        List<Integer>[] adj=new List[idToWord.size()];
        for (int i = 0; i < adj.length; i++) {
            adj[i]=new ArrayList<>();
        }
        for (int i = 0; i < wordToIdx.size(); i++) {
            for (int j = i+1; j < wordToIdx.size(); j++) {
                if (transformCheck(idToWord.get(i),idToWord.get(j))){
                    adj[i].add(j);
                    adj[j].add(i);
                }
            }
        }
        return adj;
    }

    private boolean transformCheck(String word1,String word2){
        int differences=0;
        for (int i = 0; i < word1.length() && differences < 2; i++) {
            if (word1.charAt(i)!=word2.charAt(i)){
                differences++;
            }
        }
        return differences==1;
    }
}
