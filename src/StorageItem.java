import java.util.Comparator;
import java.sql.Timestamp;


public abstract class StorageItem {
    public int size;
    public  String name;
    public final Timestamp date;


    public StorageItem(String name){
        this.name = name;
        this.date = getDate();
    }


    public String getName() {
        return name;
    }

    public abstract int getSize();

    void printTree(SortingField field){
        // sends it to an aux function with new variable as dipth strting from 0
        // sort it by given field
        //string append  |    x dipth times + name
        //for strage ithem in this items call the same func again with dipth +1
        //same func for file without the forloop
    }

    public Timestamp getDate(){
        long starting_range = Timestamp.valueOf("2017-01-01 00:00:00").getTime();
        long range_end = Timestamp.valueOf("2021-12-31 23:59:59").getTime();

        long diff = range_end - starting_range;

        long random = Math.abs(Main.rnd.nextLong());

        Timestamp date = new Timestamp((random % diff) + starting_range);

        return date;
    }
}


class sortByName implements Comparator<StorageItem>{
    @Override
    public int compare(StorageItem o1, StorageItem o2) {
        return o1.name.compareTo(o2.name);
    }
}

class sortBySize implements Comparator<StorageItem>{
    @Override
    public int compare(StorageItem o1, StorageItem o2) {
        o1.size = o1.getSize();
        o2.size = o2.getSize();
        if(o1.size == o2.size){return o1.name.compareTo(o2.name);}
        return o1.size - o2.size;
    }
}


class sortByDate implements Comparator<StorageItem> {
    @Override
    public int compare(StorageItem o1, StorageItem o2) {
        if(o1.date.compareTo(o2.date) == 0){return o1.name.compareTo(o2.name);}
        return o1.date.compareTo(o2.date);
    }
}
