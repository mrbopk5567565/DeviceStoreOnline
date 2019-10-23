package thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.model;

public class Cart {
    private int id;
    private String tensp;
    private long giasp;
    private String hinhanhsp;
    private int soluong;

    public Cart(int id, String tensp, long giasp, String hinhanhsp, int soluong) {
        this.id = id;
        this.tensp = tensp;
        this.giasp = giasp;
        this.hinhanhsp = hinhanhsp;
        this.soluong = soluong;
    }

    public int getId() {
        return id;
    }

    public Cart setId(int id) {
        this.id = id;
        return this;
    }

    public String getTensp() {
        return tensp;
    }

    public Cart setTensp(String tensp) {
        this.tensp = tensp;
        return this;
    }

    public long getGiasp() {
        return giasp;
    }

    public Cart setGiasp(long giasp) {
        this.giasp = giasp;
        return this;
    }

    public String getHinhanhsp() {
        return hinhanhsp;
    }

    public Cart setHinhanhsp(String hinhanhsp) {
        this.hinhanhsp = hinhanhsp;
        return this;
    }

    public int getSoluong() {
        return soluong;
    }

    public Cart setSoluong(int soluong) {
        this.soluong = soluong;
        return this;
    }
}
