package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public final class TaskRepository {
    private static final File path = new File("Resources.txt");


    static public <T> Status writeList(List<T> list) {

        if (list == null || list.isEmpty()) return Status.EMPTY;

        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(path))) {
            outputStream.writeObject(list);
            outputStream.flush();
            return Status.COMPLETE;
        } catch (IOException e) {
            return Status.ERROR;
        }
    }
    static public void removeSources(){
        path.delete();
    }
    static public <T> List<T> readList() throws IOException, ClassNotFoundException {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(path))) {
            return (List<T>) inputStream.readObject();
        }
    }

}
