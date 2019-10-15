package thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.api.modelapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseLoaisp {

@SerializedName("id")
@Expose
private int id;
@SerializedName("tenloaisp")
@Expose
private String tenloaisp;
@SerializedName("hinhanhloaisp")
@Expose
private String hinhanhloaisp;

public int getId() {
return id;
}

public void setId(int id) {
this.id = id;
}

public String getTenloaisp() {
return tenloaisp;
}

public void setTenloaisp(String tenloaisp) {
this.tenloaisp = tenloaisp;
}

public String getHinhanhloaisp() {
return hinhanhloaisp;
}

public void setHinhanhloaisp(String hinhanhloaisp) {
this.hinhanhloaisp = hinhanhloaisp;
}

}