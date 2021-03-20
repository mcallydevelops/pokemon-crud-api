package com.mcallydevelops.models;

public class Pokemon {

    public Integer idl;
    public String name;

    public Pokemon() {
    }

    public Pokemon(Integer idl, String name) {
        this.idl = idl;
        this.name = name;
    }

    public Integer getIdl() {
        return idl;
    }

    public void setIdl(Integer idl) {
        this.idl = idl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
