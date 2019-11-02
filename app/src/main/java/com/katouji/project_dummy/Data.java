package com.katouji.project_dummy;

public class Data {

    String id;
    String nama;
    String matakuliah;

    public Data() {

    }

    public Data(String id, String nama, String matakuliah) {
        this.id = id;
        this.nama = nama;
        this.matakuliah = matakuliah;
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getMatakuliah() {
        return matakuliah;
    }
}
