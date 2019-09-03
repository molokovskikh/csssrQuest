package com.csssr.service;

import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by Sergey Molokovskikh on 03.09.2019.
 * Форматер групп слов в строку
 */
@Service
public class WordGroupsFormatter {

    public static final String LEFT_BRACKET = "[";
    public static final String RIGHT_BRACKET = "]";
    public static final String EQUAL = "=";
    public static final String COMMA = ",";
    private static final String SPACE = " ";

    /**
     * Преобразование групп слов в строку, для вывода в консоль
     *
     * @param wordGroups
     * @return
     */
    public String print(Map<String, Set<String>> wordGroups) {
        StringBuilder stringBuilder = new StringBuilder();
        if (wordGroups.size() > 0) {
            stringBuilder.append(LEFT_BRACKET);
            appendWordGroup(wordGroups, stringBuilder);
            stringBuilder.append(RIGHT_BRACKET);
        }
        return stringBuilder.toString();
    }

    /**
     * Добавление группы
     *
     * @param wordGroups    Группы слов
     * @param stringBuilder Общий результат
     */
    private void appendWordGroup(Map<String, Set<String>> wordGroups, StringBuilder stringBuilder) {
        Iterator<String> wordGroupIterator = wordGroups.keySet().iterator();
        while (wordGroupIterator.hasNext()) {
            String wordGroup = wordGroupIterator.next();

            if (wordGroups.get(wordGroup).size() == 0)
                continue;

            stringBuilder
                    .append(wordGroup)
                    .append(EQUAL)
                    .append(LEFT_BRACKET);

            appendWord(wordGroups, stringBuilder, wordGroup);

            stringBuilder.append(RIGHT_BRACKET);

            if (wordGroupIterator.hasNext()) {
                stringBuilder
                        .append(COMMA)
                        .append(SPACE);
            }
        }
    }

    /**
     * Добавления слова к группе
     *
     * @param wordGroups
     * @param stringBuilder
     * @param wordGroup
     */
    private void appendWord(Map<String, Set<String>> wordGroups, StringBuilder stringBuilder, String wordGroup) {
        Iterator<String> wordIterator = wordGroups.get(wordGroup).iterator();
        while (wordIterator.hasNext()) {
            String word = wordIterator.next();
            stringBuilder.append(word);
            if (wordIterator.hasNext()) {
                stringBuilder
                        .append(COMMA)
                        .append(SPACE);
            }
        }
    }
}
