package com.scaler.productservice_sep2025.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class productController {
    @GetMapping("/printHello_Word")
    public ArrayList<String> printHello_Word(){
        ArrayList<String> name=new ArrayList<>();
        for(int i=0;i<10;i++){
            name.add("Hello World");
        }
//        for(String s:name){
//            System.out.print(s+" ");
//        }
//        System.out.println();
        return name;

    }

}
