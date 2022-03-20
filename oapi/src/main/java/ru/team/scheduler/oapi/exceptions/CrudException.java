package ru.team.scheduler.oapi.exceptions;

import ru.team.scheduler.oapi.constants.CRUD;

import java.util.function.Supplier;

public class CrudException extends RuntimeException {
    private CRUD crud;
    private Integer id;
    private String message;
    private String nameTable;

    public CrudException(String nameTable, CRUD crud, Integer id, String message) {
        this.nameTable = nameTable;
        this.crud = crud;
        this.id = id;
        this.message = message;
    }
    public CrudException(String nameTable, CRUD crud, String message) {
        this.nameTable = nameTable;
        this.crud = crud;
        this.message = message;
    }
    public CrudException(CRUD crud, String message) {
        this.crud = crud;
        this.message = message;
    }

    @Override
    public String toString() {
        return "CrudException{" +
                "crud=" + crud +
                ", id=" + ((id==0)? 0: id) +
                ", message='" + ((message==null)? "?": message) + '\'' +
                ", nameTable='" + ((nameTable==null)? "?": nameTable) + '\'' +
                '}';
    }
}
