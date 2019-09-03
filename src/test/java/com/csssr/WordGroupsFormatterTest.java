package com.csssr;

import com.csssr.service.WordGroupsFormatter;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

/**
 * Created by Sergey Molokovskikh on 03.09.2019.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WordGroupsFormatter.class})
public class WordGroupsFormatterTest {

    // И кстати и у Вас в тестовом задание в этой строке ошибка
    // вместо русской 'с' [эс] у Вас латинское 'c' [си]
    private static final String ACCEPTANCE_CRITERIA = "[б=[биржа, бокс, болт], с=[сaпог, сарай]]";

    @Autowired
    private WordGroupsFormatter wordGroupsFormatter;

    @Test
    public void print() throws Exception {

        LinkedHashMap<String, Set<String>> wordsGroups = new LinkedHashMap<>();
        LinkedHashSet<String> group1 = new LinkedHashSet<>();
        LinkedHashSet<String> group2 = new LinkedHashSet<>();
        wordsGroups.put("б", group1);
        wordsGroups.put("с", group2);

        group1.add("биржа");
        group1.add("бокс");
        group1.add("болт");

        group2.add("сaпог");
        group2.add("сарай");

        String result = wordGroupsFormatter.print(wordsGroups);
        Assert.assertEquals(ACCEPTANCE_CRITERIA, result);
    }

}