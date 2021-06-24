/**
 * this class extends storageItem
 * represents files
 * each file has a name a date and ext and a string of content
 */
public class File extends StorageItem {
    public String ext;
    public StringBuilder content;

    /***
     * classes constructor
     * @param name name of the file
     * @param ext the extention of the file
     */
    public File(String name, String ext){
        super(name);
        this.ext = ext;
        this.content = new StringBuilder();
    }

    /***
     * @return returns the file size
     */
    @Override
    public int getSize() {
        return content.length();
    }

    /***
     * returns the string name with extension
     * @return  the string name with extension
     *
     */
    @Override
    public String getName() {
        return this.name + '.' + this.ext;
    }

    /***
     * adds content to the files string of content
     * @param contentToAdd given content to add
     */
    void addContent(String contentToAdd){
        this.content.append(contentToAdd);
    }

    /***
     * the method prints the files name size and date of creation, and its content
     */
    void printContent(){
        System.out.println(this.getName() + " Size: " + getSize() + "MB" + " Created: " + this.date);
        System.out.println(this.content);
    }

    /***
     * prints the name of storageItem uncluding the amount of "|   " needed
     * @param field specific way to sort the items before printing
     * @param depth the number of folders we are in
     */
    @Override
    void printTreeAux(SortingField field, int depth) {
        int temp_depth = depth;
        StringBuilder printing_structure = new StringBuilder();
        while (temp_depth>0){//fill building structure
            printing_structure.append("|    ");
            temp_depth--;
        }
        printing_structure.append(this.getName());
        System.out.println(printing_structure);
    }
}
