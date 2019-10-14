package thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.model;

public class Loaisp {
    private int id;
    private String tenLoaiSp;
    private String hinhAnhLoaiSp;

    public Loaisp(int id, String tenLoaiSp, String hinhAnhLoaiSp) {
        this.id = id;
        this.tenLoaiSp = tenLoaiSp;
        this.hinhAnhLoaiSp = hinhAnhLoaiSp;
    }

    public int getId() {
        return id;
    }

    public Loaisp setId(int id) {
        this.id = id;
        return this;
    }

    public String getTenLoaiSp() {
        return tenLoaiSp;
    }

    public Loaisp setTenLoaiSp(String tenLoaiSp) {
        this.tenLoaiSp = tenLoaiSp;
        return this;
    }

    public String getHinhAnhLoaiSp() {
        return hinhAnhLoaiSp;
    }

    public Loaisp setHinhAnhLoaiSp(String hinhAnhLoaiSp) {
        this.hinhAnhLoaiSp = hinhAnhLoaiSp;
        return this;
    }
}
