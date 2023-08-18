package com.barisdev.Url.Shortener.util;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Random;
import java.util.stream.Collectors;

@Component
public class RandomStringGenerator {

    private int refLen=5;

    public String generateRandomString(){
        Random random=new Random();

        String generated="";

        var letters="abcdefghijklmnprstyzwx123456789"
                .toUpperCase()
                .chars()
                .mapToObj(x->(char)x)
                .collect(Collectors.toList());

        Collections.shuffle(letters);

        for(int i=0;i<refLen;i++){
            generated+=letters.get(random.nextInt(letters.size()));
        }
        return generated;

    }


}
