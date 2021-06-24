
import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;

/***
 * this class represents a folder
 * it extends storageItem so it has a name and a creation date
 * and ait has an array list of storage items
 */
public class Folder extends StorageItem {
    ArrayList<StorageItem> items;

    /***
     * classes constructor
     * @param name name of the folder to build
     */
    public Folder(String name) {
        super(name);
        items = new ArrayList<>();
    }

    /***
     * @return the size of all the files in this folders and the folders in it
     */
    @Override
    public int getSize() {
        int counter = 0;
        for (StorageItem item : this.items) {
            counter += item.getSize();
        }
        return counter;
    }

    /***
     * this function checks if we can add an item based on wither we have an item of the same name or not
     * @param item item to add
     * @return true if we cann add the item and false else
     */
    boolean addItem(StorageItem item) {
        for (StorageItem item1 : items) {
            if (item.name.equals(item1.name)) {
                return false;
            }
        }
        this.items.add(item);
        return true;
    }

    /***
     * this method takes a string of a certin path of file and returns wither there is such file or not
     * @param path string the wanted path to search for
     * @return the file we searched for
     */
    File findFile(String path) {
        String[] parts = path.split("/");
        return findFile2(parts, 0);

    }

    /***
     * an aux function for findFile that takes the file as a string array
     * it searches or the file in all the folders
     * @param parts the file path split
     * @param i the pat of file path to search for
     * @return the file if found, null if not
     */
    File findFile2(String[] parts, int i) {
        if (i >= parts.length) {//won't happen
            return null;
        }

        for (StorageItem item : this.items) {
            boolean sameName = item.getName().equals(parts[i]);
            if (sameName) {
                if (i == parts.length - 1) {//the part of the file name
                    if (item instanceof File) {//file is found
                        return (File) item;
                    } else {
                        return null;
                    }
                } else if (item instanceof Folder) {//same name not last part and instance of folder
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

    /***
     * sorts the array list of storage items in this folder and prints the tree
     * @param field specific way to sort the items before printing
     * @param dipth the number of folders we are in
     */
    @Override
    void printTreeAux(SortingField field, int dipth) {
        switch (field) {//sort
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
        while (temp_dipth>0){//fill building structure
            printing_structure.append("|    ");
            temp_dipth--;
        }
        printing_structure.append(this.getName());
        System.out.println(printing_structure);

        for (StorageItem item : this.items) {

            item.printTreeAux(field, dipth + 1);
        }
    }
}

