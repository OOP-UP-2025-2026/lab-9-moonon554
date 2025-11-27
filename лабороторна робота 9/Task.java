package ua.opnu;

import java.util.*;

public class Task {

    public static void main(String[] args) {
        Task task = new Task();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\nВиберіть завдання (1-16) або 0 для виходу:");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 0 -> { System.out.println("Вихід."); return; }
                case 1 -> {
                    List<String> list = readStringList(sc);
                    task.removeShorterStrings(list);
                    System.out.println(list);
                }
                case 2 -> {
                    List<String> list = readStringList(sc);
                    task.stutter(list);
                    System.out.println(list);
                }
                case 3 -> {
                    List<String> list = readStringList(sc);
                    task.switchPairs(list);
                    System.out.println(list);
                }
                case 4 -> {
                    List<String> list = readStringList(sc);
                    task.removeDuplicates(list);
                    System.out.println(list);
                }
                case 5 -> {
                    List<String> list = readStringList(sc);
                    task.markLength4(list);
                    System.out.println(list);
                }
                case 6 -> {
                    Queue<Integer> q = readIntQueue(sc);
                    System.out.println(task.isPalindrome(q));
                }
                case 7 -> {
                    Queue<Integer> q = readIntQueue(sc);
                    task.reorder(q);
                    System.out.println(q);
                }
                case 8 -> {
                    Queue<Integer> q = readIntQueue(sc);
                    task.rearrange(q);
                    System.out.println(q);
                }
                case 9 -> {
                    Set<String> set = readStringSet(sc);
                    System.out.println(task.maxLength(set));
                }
                case 10 -> {
                    Set<String> set = readStringSet(sc);
                    task.removeEvenLength(set);
                    System.out.println(set);
                }
                case 11 -> {
                    List<Integer> list1 = readIntList(sc);
                    List<Integer> list2 = readIntList(sc);
                    System.out.println(task.numInCommon(list1, list2));
                }
                case 12 -> {
                    Map<String, String> map = readStringMap(sc);
                    System.out.println(task.isUnique(map));
                }
                case 13 -> {
                    Map<String, Integer> m1 = readIntMap(sc);
                    Map<String, Integer> m2 = readIntMap(sc);
                    System.out.println(task.intersect(m1, m2));
                }
                case 14 -> {
                    Map<Integer, String> map = readMapIntString(sc);
                    System.out.println(task.reverse(map));
                }
                case 15 -> {
                    Map<String, Integer> map = readIntMap(sc);
                    System.out.println(task.rarest(map));
                }
                case 16 -> {
                    List<Integer> list = readIntList(sc);
                    System.out.println(task.maxOccurrences(list));
                }
                default -> System.out.println("Невірний вибір.");
            }
        }
    }

    // ---------------------- Реалізація завдань -----------------------

    public void removeShorterStrings(List<String> list) {
        for (int i = 0; i + 1 < list.size(); i += 2) {
            if (list.get(i).length() <= list.get(i + 1).length()) list.remove(i);
            else list.remove(i + 1);
        }
    }

    public void stutter(List<String> list) {
        for (int i = 0; i < list.size(); i += 2) list.add(i + 1, list.get(i));
    }

    public void switchPairs(List<String> list) {
        for (int i = 0; i + 1 < list.size(); i += 2) {
            String tmp = list.get(i);
            list.set(i, list.get(i + 1));
            list.set(i + 1, tmp);
        }
    }

    public void removeDuplicates(List<String> list) {
        for (int i = list.size() - 1; i > 0; i--) {
            if (list.get(i).equals(list.get(i - 1))) list.remove(i);
        }
    }

    public void markLength4(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).length() == 4) {
                list.add(i, "****");
                i++;
            }
        }
    }

    public boolean isPalindrome(Queue<Integer> queue) {
        Stack<Integer> stack = new Stack<>();
        int n = queue.size();
        List<Integer> temp = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int val = queue.poll();
            stack.push(val);
            queue.add(val);
            temp.add(val);
        }
        for (int val : temp) queue.add(queue.poll()); // відновлення черги
        for (int val : temp) {
            if (stack.pop() != val) return false;
        }
        return true;
    }

    public void reorder(Queue<Integer> queue) {
        Stack<Integer> stack = new Stack<>();
        List<Integer> temp = new ArrayList<>(queue);
        queue.clear();
        temp.sort(Comparator.naturalOrder());
        queue.addAll(temp);
    }

    public void rearrange(Queue<Integer> queue) {
        Stack<Integer> stack = new Stack<>();
        int size = queue.size();
        List<Integer> evens = new ArrayList<>();
        List<Integer> odds = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            int val = queue.poll();
            if (val % 2 == 0) evens.add(val);
            else odds.add(val);
        }
        queue.addAll(evens);
        queue.addAll(odds);
    }

    public int maxLength(Set<String> set) {
        int max = 0;
        for (String s : set) if (s.length() > max) max = s.length();
        return max;
    }

    public void removeEvenLength(Set<String> set) {
        set.removeIf(s -> s.length() % 2 == 0);
    }

    public int numInCommon(List<Integer> list1, List<Integer> list2) {
        Set<Integer> set1 = new HashSet<>(list1);
        Set<Integer> set2 = new HashSet<>(list2);
        set1.retainAll(set2);
        return set1.size();
    }

    public boolean isUnique(Map<String, String> map) {
        Set<String> values = new HashSet<>();
        for (String val : map.values()) {
            if (!values.add(val)) return false;
        }
        return true;
    }

    public Map<String, Integer> intersect(Map<String, Integer> map1, Map<String, Integer> map2) {
        Map<String, Integer> result = new HashMap<>();
        for (String key : map1.keySet()) {
            if (map2.containsKey(key) && map2.get(key).equals(map1.get(key))) result.put(key, map1.get(key));
        }
        return result;
    }

    public Map<String, Integer> reverse(Map<Integer, String> map) {
        Map<String, Integer> result = new HashMap<>();
        for (Map.Entry<Integer, String> e : map.entrySet()) {
            result.putIfAbsent(e.getValue(), e.getKey());
        }
        return result;
    }

    public int rarest(Map<String, Integer> map) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int val : map.values()) count.put(val, count.getOrDefault(val, 0) + 1);
        int minCount = Integer.MAX_VALUE;
        int minVal = Integer.MAX_VALUE;
        for (int val : count.keySet()) {
            if (count.get(val) < minCount || (count.get(val) == minCount && val < minVal)) {
                minCount = count.get(val);
                minVal = val;
            }
        }
        return minVal;
    }

    public int maxOccurrences(List<Integer> list) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int val : list) count.put(val, count.getOrDefault(val, 0) + 1);
        int max = 0;
        for (int val : count.values()) if (val > max) max = val;
        return max;
    }

    // -------------------- Методи введення -----------------------

    private static List<String> readStringList(Scanner sc) {
        System.out.println("Введіть рядки через пробіл:");
        return new ArrayList<>(Arrays.asList(sc.nextLine().split("\\s+")));
    }

    private static List<Integer> readIntList(Scanner sc) {
        System.out.println("Введіть числа через пробіл:");
        List<Integer> list = new ArrayList<>();
        for (String s : sc.nextLine().split("\\s+")) list.add(Integer.parseInt(s));
        return list;
    }

    private static Queue<Integer> readIntQueue(Scanner sc) {
        return new ArrayDeque<>(readIntList(sc));
    }

    private static Set<String> readStringSet(Scanner sc) {
        return new HashSet<>(readStringList(sc));
    }

    private static Map<String, String> readStringMap(Scanner sc) {
        Map<String, String> map = new HashMap<>();
        System.out.println("Введіть пари ключ значення через пробіл (key value key value ...):");
        String[] arr = sc.nextLine().split("\\s+");
        for (int i = 0; i + 1 < arr.length; i += 2) map.put(arr[i], arr[i + 1]);
        return map;
    }

    private static Map<String, Integer> readIntMap(Scanner sc) {
        Map<String, Integer> map = new HashMap<>();
        System.out.println("Введіть пари ключ значення через пробіл (key value key value ...):");
        String[] arr = sc.nextLine().split("\\s+");
        for (int i = 0; i + 1 < arr.length; i += 2) map.put(arr[i], Integer.parseInt(arr[i + 1]));
        return map;
    }

    private static Map<Integer, String> readMapIntString(Scanner sc) {
        Map<Integer, String> map = new HashMap<>();
        System.out.println("Введіть пари ключ значення через пробіл (key value key value ...):");
        String[] arr = sc.nextLine().split("\\s+");
        for (int i = 0; i + 1 < arr.length; i += 2) map.put(Integer.parseInt(arr[i]), arr[i + 1]);
        return map;
    }
}
