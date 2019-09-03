package com.csssr.service;

import org.springframework.stereotype.Component;

import java.text.Collator;
import java.util.Comparator;

/**
 * Created by Sergey Molokovskikh on 03.09.2019.
 * Компаратор для сортировки слов в группе
 */
@Component
public class GroupWordsComparator implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {

        int res = Integer.compare(o2.length(), o1.length());
        if (res != 0)
            return res;
        //Для правильного сортировки слов в русском алфавите (например с буквой 'Ё')
        return Collator.getInstance().compare(o1,o2);
    }
}
