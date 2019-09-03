package com.csssr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.Collator;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by Sergey Molokovskikh on 03.09.2019.
 * Сервис преобразования входной строки со списком слов в группы слов
 */
@Service
public class CSSSRQuestService {

    public static final String SPACE = "\\s+";

    @Autowired
    private Comparator<String> groupWordsComparator;

    private final Function<List<String>, Set<String>> setFinisher = words -> {
        if (words.size() <= 1)
            return Collections.emptySet();

        Collections.sort(words, groupWordsComparator);

        return new LinkedHashSet<>(words);
    };

    public Map<String, Set<String>> solve(String inputString) {

        String[] words = inputString.split(SPACE);
        Map<String, Set<String>> wordGroups = Arrays.stream(words)
                .collect(Collectors.groupingBy(s -> s.substring(0, 1),
                        Collectors.collectingAndThen(Collectors.toList(), setFinisher)))
                .entrySet().stream()
                .filter(stringSetEntry -> stringSetEntry.getValue().size() > 0)
                .sorted(Map.Entry.comparingByKey((o1, o2) -> Collator.getInstance().compare(o1, o2)))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        return wordGroups;
    }

}

