package com.jerry.mekanism_extras.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

public final class ListHelper {
    /**
     * Calculates all two-element permutations in a list.
     * @param list the list
     * @return the permutation list
     * @param <T> the generic type of list
     */
    public static <T> List<Pair<T, T>> permutation(List<T> list) {
        List<Pair<T, T>> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            int finalI = i;
            result.addAll(list.subList(i + 1, list.size())
                    .stream()
                    .map(e -> Pair.of(list.get(finalI), e))
                    .toList());
        }
        return result;
    }
}
