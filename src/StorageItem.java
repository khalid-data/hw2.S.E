import java.util.Comparator;
import java.sql.Timestamp;

/***
 * abstract class that represents a storage item
 * its parameters are size name and a timestamp
 */
public abstract class StorageItem {
    public int size;
    public  String name;
    public final Timestamp date;

    /***
     * the classes constructor
     * @param name name of the storageitem
     */
    public StorageItem(String name){
        this.name = name;
        this.date = getDate();
    }

    /***
     * @return the storage items name
     */
    public String getName() {
        return name;
    }

    /***
     * abstract method overwritten in file and folder classes
     * @return the storage items size
     */
    public abstract int getSize();

    /***
     * prints the tree of the storage items
     * @param field specific way to sort the items before printing
     */
    void printTree(SortingField field){
        printTreeAux(field, 0);
    }

    /***
     * this method is an auxiliary method for printTree method
     * it takes an extra depth param
     * the method is overwritten in file and folder classes
     * @param field specific way to sort the items before printing
     * @param dipth the number of folders we are in
     */
    void printTreeAux(SortingField field, int dipth){

    }

    /***
     * gives timestamp
     * @return time stamp in the wanted period of time
     */
    public Timestamp getDate(){
        long starting_range = Timestamp.valueOf("2017-01-01 00:00:00").getTime();
        long range_end = Timestamp.valueOf("2021-12-31 23:59:59").getTime();
        long diff = range_end - starting_range;
        long random = Math.abs(Main.rnd.nextLong());
        Timestamp date = new Timestamp((random % diff) + starting_range);

        return date;
    }
}

/***
 * compares strings
 * returns 0 if equal, 1 if 01 is bigger, -1 else
 */
class sortByName implements Comparator<StorageItem>{
    @Override
    public int compare(StorageItem o1, StorageItem o2) {
        return o1.name.compareTo(o2.name);
    }
}

/***
 * compares sizes
 * returns 0 if equal, 1 if 01 is bigger, -1 else
 */
class sortBySize implements Comparator<StorageItem>{
    @Override
    public int compare(StorageItem o1, StorageItem o2) {
        o1.size = o1.getSize();
        o2.size = o2.getSize();
        if(o1.size == o2.size){return o1.name.compareTo(o2.name);}
        return o1.size - o2.size;
    }
}

/***
 * compares dates
 * returns 0 if equal, 1 if 01 is bigger, -1 else
 */
class sortByDate implements Comparator<StorageItem> {
    @Override
    public int compare(StorageItem o1, StorageItem o2) {
        if(o1.date.compareTo(o2.date) == 0){return o1.name.compareTo(o2.name);}
        return o1.date.compareTo(o2.date);
    }
}
