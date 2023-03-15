package edu.geekhub.product.image;

public class Image {

    private int id;
    private String name;

    private String originalFileName;

    private int size;

    private String contentType;

    private byte[] bytes;

    public Image() {
    }

    public Image(int id, String name, String originalfilename, int size, String contenttype, byte[] bytes) {
    this.id=id;
    this.name=name;
    this.originalFileName=originalfilename;
    this.size=size;
    this.contentType=contenttype;
    this.bytes=bytes;

    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }


    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

}

