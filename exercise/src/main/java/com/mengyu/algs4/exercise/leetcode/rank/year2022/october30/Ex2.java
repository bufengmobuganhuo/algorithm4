package com.mengyu.algs4.exercise.leetcode.rank.year2022.october30;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yuzhang
 * @date 2022/10/30 09:58
 * TODO
 */
public class Ex2 {
    public List<List<String>> mostPopularCreator(String[] creators, String[] ids, int[] views) {
        Map<String, Info> map = new HashMap<>();
        int len = creators.length;
        long top = 0;
        for (int i = 0; i < len; i++) {
            String creator = creators[i];
            String id = ids[i];
            int view = views[i];
            Info info = map.computeIfAbsent(creator, key -> new Info());
            info.popular += view;
            if (top < info.popular) {
                top = info.popular;
            }
            if (info.maxView < view || info.id == null || (info.maxView == view && info.id.compareTo(id) > 0)) {
                info.maxView = view;
                info.id = id;
            }
        }
        List<List<String>> ans = new ArrayList<>();
        for (Map.Entry<String, Info> entry : map.entrySet()) {
            if (entry.getValue().popular == top) {
                List<String> list = new ArrayList<>();
                list.add(entry.getKey());
                list.add(entry.getValue().id);
                ans.add(list);
            }
        }
        return ans;
    }

    static class Info {
        String id;
        long popular;
        int maxView;
    }
}
