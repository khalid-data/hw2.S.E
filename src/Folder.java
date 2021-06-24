import com.sun.source.tree.WhileLoopTree;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;

public class Folder extends StorageItem {
    ArrayList<StorageItem> items;

    public Folder(String name) {
        super(name);
        items = new ArrayList<>();
    }

    @Override
    public int getSize() {
        int counter = 0;
        for (StorageItem item : this.items) {
            counter += item.getSize();
        }
        return counter;
    }

    boolean addItem(StorageItem item) {
        for (StorageItem item1 : items) {
            if (item.name.equals(item1.name)) {
                return false;
            }
        }
        items.add(item);
        return true;
    }


    File findFile(String path) {
        String[] parts = path.split("/");
        return findFile2(parts, 0);

    }


    File findFile2(String[] parts, int i) {
        //please change me!!
        if (i >= parts.length) {
            return null;
        }

        for (StorageItem item : this.items) {

            boolean sameName = item.getName().equals(parts[i]);
            if (sameName) {
                if (i == parts.length - 1) {
                    if (item instanceof File) {
                        return (File) item;
                    } else {
                        return null;
                    }
                } else if (item instanceof Folder) {
                    File temp_file = ((Folder) item).findFile2(parts, i + 1);
                    if (temp_file != null) {
                        return temp_file;
                    }
                }

            } else if (item instanceof Folder && i == 0) {
                File temp = ((Folder) item).findFile2(parts, i);
                if (temp != null) {
                    return temp;
                }
            }
        }
        return null;
    }


    @Override
    void printTreeAux(SortingField field, int dipth) {
        switch (field) {
            case NAME:
                Collections.sort(this.items, new sortByName());
                break;
            case SIZE:
                Collections.sort(this.items, new sortBySize());
                break;
            case DATE:
                Collections.sort(this.items, new sortByDate());
                break;
        }

        int temp_dipth = dipth;

        StringBuilder printing_structure = new StringBuilder();
        while(temp_dipth>0){
            printing_structure.append("|    ");
            temp_dipth--;
        }
        printing_structure.append(this.getName());
        System.out.println(printing_structure);

        for (StorageItem item : this.items){

            item.printTreeAux(field, dipth+1);
        }
    }
}