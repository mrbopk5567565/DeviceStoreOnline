package thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.model;

public class Sanpham {
    private int id;
    private String tensp;
    private int giasp;
    private String hinhanhsp;
    private String motasp;
    private int idsp;

    public Sanpham(int id, String tensp, int giasp, String hinhanhsp, String motasp, int idsp) {
        this.id = id;
        this.tensp = tensp;
        this.giasp = giasp;
        this.hinhanhsp = hinhanhsp;
        this.motasp = motasp;
        this.idsp = idsp;
    }

    public int getId() {
        return id;
    }

    public Sanpham setId(int id) {
        this.id = id;
        return this;
    }

    public String getTensp() {
        return tensp;
    }

    public Sanpham setTensp(String tensp) {
        this.tensp = tensp;
        return this;
    }

    public int getGiasp() {
        return giasp;
    }

    public Sanpham setGiasp(int giasp) {
        this.giasp = giasp;
        return this;
    }

    public String getHinhanhsp() {
        return hinhanhsp;
    }

    public Sanpham setHinhanhsp(String hinhanhsp) {
        this.hinhanhsp = hinhanhsp;
        return this;
    }

    public String getMotasp() {
        return motasp;
    }

    public Sanpham setMotasp(String motasp) {
        this.motasp = motasp;
        return this;
    }

    public int getIdsp() {
        return idsp;
    }

    public Sanpham setIdsp(int idsp) {
        this.idsp = idsp;
        return this;
    }
}
