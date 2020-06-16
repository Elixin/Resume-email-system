package com.example.email.util;

import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Component
public class ContextSeparate {
    private int entercount = 0;
    private boolean comma = false;
    private List<String> requestvalues;

    public List<String> getRequestvalues() {
        return requestvalues;
    }

    /**
     * 将需要进行解析的字符串传入,
     * 并通过\n或者逗号判断字符串形状
     *
     * @param context
     */
    public void setText(String context) {
        System.out.println(context);
        char[] contextchars = context.toCharArray();
        for (int i = 0; i < contextchars.length; i++) {
            if (contextchars[i] == '\n') {
                entercount++;
            }
            if (contextchars[i] == ',') {
                comma = true;
            }
        }
        requestvalues = discriminate(context);


    }
    /**
     * 通过判断\n或者逗号数量进行分别区分并分解
     *
     * @param context
     * @return
     */
    private List<String> discriminate(String context) {
        if (entercount > 3) {
            String[] s = context.split("\n");
            s = getStrings(s);
            requestvalues = getstringvalues(s);
        } else if (comma) {
            String[] s = context.split(",");
            s = getStrings(s);
            requestvalues = getstringvalues(s);
        } else {
            String[] s = context.split(" ");
            s = getStrings(s);
            requestvalues = getstringvalues(s);
        }
        return requestvalues;
    }

    private String[] getStrings(String[] s) {
        List<String> ss = Arrays.asList(s);
        List<String> strings = new ArrayList<>(ss);
        for (int i = strings.size() - 1; i > 0; i--) {
            if (strings.get(i).length() < 3) {
                strings.remove(i);
            }
        }
        s = strings.toArray(new String[strings.size()]);
        for (int i = 0; i < s.length; i++) {
            System.out.println(s[i]);
        }
        return s;
    }

    /**
     * 获得最终值
     *
     * @param s
     * @return
     */
    private List<String> getstringvalues(String[] s) {
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < s.length; i++) {
            String[] resolve = s[i].split(":|：");
            strings.add(resolve[resolve.length - 1]);
        }
        return strings;
    }

}
