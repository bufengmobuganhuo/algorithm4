package com.mengyu.algs4.exercise.leetcode.dfs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yu zhang
 */
public class Ex690 {

    public static void main(String[] args) {

    }

    private int importance;

    private Map<Integer, Employee> map;

    public int getImportance(List<Employee> employees, int id) {
        this.importance = Integer.MIN_VALUE;
        this.map = new HashMap<>();
        for (Employee employ : employees) {
            map.put(employ.id, employ);
        }
        for (Employee employ : employees) {
            dfs(employ, id);
            if (this.importance != Integer.MIN_VALUE) {
                return this.importance;
            }
        }
        return this.importance;
    }

    private int dfs(Employee employee, int id) {
        if (employee == null || importance != Integer.MIN_VALUE) {
            return 0;
        }
        int importance = employee.importance;
        if (employee.subordinates == null || employee.subordinates.isEmpty()) {
            if (employee.id == id) {
                this.importance = importance;
            }
            return importance;
        }
        for (int subordinate : employee.subordinates) {
            importance += dfs(map.get(subordinate), id);
        }
        if (id == employee.id) {
            this.importance = importance;
        }
        return importance;
    }

    private class Employee {
        public int id;
        public int importance;
        public List<Integer> subordinates;
    };
}
