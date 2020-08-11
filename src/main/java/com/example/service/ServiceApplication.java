package com.example.service;

import com.example.service.utils.FileParser;


public class ServiceApplication {
    public static void main(String[] args) {

        FileParser parser = new FileParser();

        //Here in "args[]" you have to put your own file path
        parser.parseFile(args[0]);

    }

}
