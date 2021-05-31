package org.practice.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Library {

    private String name;

    private int maxNoOfRacks;

    private Rack[] racksInLibrary;

    private Map<String, Book> booksMapByBookId;

    private List<UserAccount> userAccounts;

    public Library(String name, int maxNoOfRacks) {
        this.name = name;
        this.maxNoOfRacks = maxNoOfRacks;
        this.racksInLibrary = new Rack[maxNoOfRacks];
        this.booksMapByBookId = new HashMap<>();
        this.userAccounts = new ArrayList<>();
        buildRacks();
    }

    private void buildRacks(){
        for(int i = 1; i <= this.maxNoOfRacks; i++)
            racksInLibrary[i-1]= new Rack();
    }

    @Override
    public String toString() {
        return "Library{" +
                "name='" + name + '\'' +
                ", maxNoOfRacks=" + maxNoOfRacks +
                '}';
    }

    public Rack[] getRacksInLibrary() {
        return racksInLibrary;
    }

    public Map<String, Book> getBooksMapByBookId() {
        return booksMapByBookId;
    }


    public List<UserAccount> getUserAccounts() {
        return userAccounts;
    }
}
