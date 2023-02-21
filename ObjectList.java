/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordermanagement.list;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author imnvi
 */
public abstract class ObjectList<E extends IObject> extends ArrayList<E> {

    private String filePath;

    public String getFilePath() {
        return filePath;
    }

    public final void setFilePath(String filePath) {
        if (filePath != null && !filePath.isEmpty()) {
            this.filePath = filePath;
        }
    }

    public ObjectList() {
    }

    public ObjectList(String filePath) {
        setFilePath(filePath);
    }

    public boolean load() {
        try ( Scanner sc = new Scanner(new File(this.filePath))) {
            E obj;
            String data;
            while (sc.hasNextLine()) {
                data = sc.nextLine();
                if (!data.isEmpty()) {
                    obj = parseString(data);
                    if (obj != null) {
                        add(obj);
                    }
                }
            }
            return true;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ObjectList.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean save() {
        try ( BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
            for (E e : this) {
                writer.append(e.toString());
                writer.append("\n");
            }
            return true;
        } catch (IOException ex) {
            Logger.getLogger(ObjectList.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public int indexOf(Object o) {
        int idx = -1;
        for (int i = 0; i < size(); i++) {
            if (((E) o).getId().equals(get(i).getId())) {
                idx = i;
                break;
            }
        }
        return idx;
    }

    @Override
    public boolean add(E e) {
        int idx = indexOf(e);
        if (idx >= 0) {
            remove(idx);
        }
        return super.add(e);
    }

    @Override
    public void add(int index, E element) {
        int idx = indexOf(element);
        if (idx >= 0) {
            remove(idx);
        }
        super.add(index, element);
    }

    public void show() {
        for (E e : this) {
            e.output();
        }
    }

    public void showFilter(String id) {
        List<E> filterList = filter(id);
        for (E e : filterList) {
            e.output();
        }
    }
    
    public void showFilter(Date date) {
        List<E> filterList = filter(date);
        for (E e : filterList) {
            e.output();
        }
    }
    
    public abstract List<E> filter(String id);
    public abstract List<E> filter(Date date);
    protected abstract E parseString(String stringObject);
}
