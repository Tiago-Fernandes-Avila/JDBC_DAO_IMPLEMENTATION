package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class Department implements Serializable {
    private static final long serialVsersionUID = 1L;
    private Integer Id;
    private String name;

    public Department (Integer id , String name){ //constructor with paramenters
        this.Id = id;
        this.name = name;
    }

    public Department() {

    }

    public void Department(){ //construtor default
    }
    public String getName(){
        return this.name;
    }
    public void setName(String novoNome){
        this.name = novoNome;
    }
    public Integer getId(){
        return this.Id;
    }
    public void setId(Integer novoId){
        this.Id = novoId;
    }
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return Objects.equals(Id, that.Id);
    }
    @Override
    public int hashCode() {
        return Objects.hashCode(Id);
    }
    @Override
    public String toString() {
        return "Nome: " + this.getName() + " Id: " + getId();
    }
}