public class File extends StorageItem {
    public String ext;
    public StringBuilder content;

    public File(String name, String ext){
        super(name);
        this.ext = ext;
        this.content = new StringBuilder();
    }

    @Override
    public int getSize() {
        return content.length();
    }


    @Override
    public String getName() {
        return this.name + this.ext;
    }

    void addContent(String contentToAdd){
        this.content.append(contentToAdd);
    }

    void printContent(){
        System.out.println(this.name + "Size: " + getSize() + "MB" + "Created" + this.date);
        System.out.println(this.content);
    }

}
