package com.csssr;

import com.csssr.service.CSSSRQuestService;
import com.csssr.service.GroupWordsComparator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;
import java.util.Set;

/**
 * Created by Sergey Molokovskikh on 03.09.2019.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CSSSRQuestService.class, GroupWordsComparator.class})
public class CSSSRQuestServiceTest {

    private static final String INPUT_STRING = "сапог сарай арбуз болт бокс биржа";

    @Autowired
    private CSSSRQuestService csssrQuestService;

    /**
     * "[б=[биржа, бокс, болт], с=[сaпог, сарай]]"
     */
    @Test
    public void solve() {
        Map<String, Set<String>> wordGroups = csssrQuestService.solve(INPUT_STRING);
        String[] groups = wordGroups.keySet().stream().toArray(String[]::new);
        Assert.assertEquals(groups[0], "б");
        Assert.assertEquals(groups[1], "с");

        String[] group1Words = wordGroups.get("б").stream().toArray(String[]::new);
        String[] group2Words = wordGroups.get("с").stream().toArray(String[]::new);

        Assert.assertEquals(group1Words[0], "биржа");
        Assert.assertEquals(group1Words[1], "бокс");
        Assert.assertEquals(group1Words[2], "болт");

        Assert.assertEquals(group2Words[0], "сапог");
        Assert.assertEquals(group2Words[1], "сарай");
    }
}
