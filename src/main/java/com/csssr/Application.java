package com.csssr;

import com.csssr.service.CSSSRQuestService;
import com.csssr.service.WordGroupsFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Map;
import java.util.Set;

/**
 * Created by Sergey Molokovskikh on 03.09.2019.
 * Основной класс приложения
 */
@SpringBootApplication
public class Application implements ApplicationRunner {

    @Autowired
    private CSSSRQuestService csssrQuestService;

    @Autowired
    private WordGroupsFormatter wordGroupsFormatter;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String inputString = args.getSourceArgs()[0];
        Map<String, Set<String>> wordsGroups = csssrQuestService.solve(inputString);
        System.out.print(wordGroupsFormatter.print(wordsGroups));
    }
}
