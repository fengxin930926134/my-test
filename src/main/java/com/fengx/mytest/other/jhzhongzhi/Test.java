package com.fengx.mytest.other.jhzhongzhi;

import com.google.common.collect.Lists;

import java.util.*;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {
        List<Greens> all = Lists.newArrayList(
                new Greens("芦笋", 1, -2, 1),
                new Greens("胡萝卜", -2, 1, 1),
                new Greens("玉米", 1, -2, 1),
                new Greens("火龙果", 2, 2, -4),
                new Greens("榴莲", 2, -4, 2),
                new Greens("茄子", 1, 1, -2),
                new Greens("大蒜", 2, -4, 2),
                new Greens("洋葱", -4, 2, 2),
                new Greens("辣椒", 2, 2, -4),
                new Greens("石榴", -4, 2, 2),
                new Greens("土豆", 1, 1, -2),
                new Greens("南瓜", -2, 1, 1),
                new Greens("番茄", -1, -1, 2),
                new Greens("西瓜", 2, -1, -1));

        // 冬
//        List<Greens> greens = Lists.newArrayList(all.get(1), all.get(10), all.get(11), all.get(0), all.get(6));
        // 秋 大蒜/洋葱/番茄/胡萝卜/玉米/茄子/土豆/辣椒/南瓜
//        List<Greens> greens = Lists.newArrayList(all.get(6), all.get(7), all.get(12), all.get(1), all.get(2), all.get(5), all.get(10), all.get(8), all.get(11));
        // 夏 大蒜/洋葱/火龙果/石榴/西瓜/番茄/辣椒/玉米
//        List<Greens> greens = Lists.newArrayList(all.get(6), all.get(7), all.get(3), all.get(9), all.get(13), all.get(12), all.get(8), all.get(2));
        // 春 大蒜/洋葱/火龙果/石榴/西瓜/番茄/胡萝卜/玉米/茄子/土豆/芦笋/榴莲
        List<Greens> greens = Lists.newArrayList(all.get(6), all.get(7), all.get(3), all.get(9), all.get(13), all.get(12),
                all.get(1), all.get(2), all.get(5), all.get(10), all.get(0), all.get(4));

        List<List<Greens>> matchingCombinations = removeDuplicates(findMatchingCombinations(greens));

        matchingCombinations.forEach(e -> {
            if (!e.isEmpty()) {
                String collect = e.stream().map(item -> item.getName() + "(" + item.getA()
                        + "," + item.getB() + "," + item.getC() + ")").collect(Collectors.joining("、"));
                System.out.println(collect);
            }
        });

    }

    private static List<List<Greens>> removeDuplicates(List<List<Greens>> result) {
        List<List<Greens>> uniqueResult = new ArrayList<>();
        Set<String> names = new HashSet<>(result.size());

        for (List<Greens> combination : result) {
            String collect = combination.stream().map(Greens::getName).distinct().sorted().collect(Collectors.joining(","));
            if (!names.contains(collect)) {
                uniqueResult.add(combination);
                names.add(collect);
            }
        }

        return uniqueResult;
    }

    public static List<List<Greens>> findMatchingCombinations(List<Greens> greens) {
        List<List<Greens>> result = new ArrayList<>();
        List<Greens> currentCombination = new ArrayList<>();

        generateCombinations(greens, 0, currentCombination, result);

        return result;
    }

    private static void generateCombinations(List<Greens> greens, int startIndex, List<Greens> currentCombination, List<List<Greens>> result) {
        int sumA = 0;
        int sumB = 0;
        int sumC = 0;

        // 计算当前组合中的属性之和
        for (Greens green : currentCombination) {
            sumA += green.getA();
            sumB += green.getB();
            sumC += green.getC();
        }

        // 如果属性之和等于0或大于0，则将当前组合添加到结果列表中
        if (sumA >= 0 && sumB >= 0 && sumC >= 0) {
            result.add(new ArrayList<>(currentCombination));
        }

        // 递归生成下一个组合
        for (int i = startIndex; i < greens.size(); i++) {
            Greens nextGreen = greens.get(i);

            // 匹配多个相同水果的逻辑
            for (int j = 1; j <= 6; j++) {
                for (int k = 0; k < j; k++) {
                    currentCombination.add(nextGreen);
                }

                generateCombinations(greens, i + 1, currentCombination, result);

                // 移除已添加的水果
                for (int k = 0; k < j; k++) {
                    currentCombination.remove(currentCombination.size() - 1);
                }
            }
        }
    }

}
