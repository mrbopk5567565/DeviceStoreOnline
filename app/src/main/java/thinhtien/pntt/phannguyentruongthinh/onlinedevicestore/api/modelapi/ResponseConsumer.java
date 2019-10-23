package thinhtien.pntt.phannguyentruongthinh.onlinedevicestore.api.modelapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseConsumer {

@SerializedName("success")
@Expose
private Boolean success;
@SerializedName("iduser")
@Expose
private Integer iduser;

public Boolean getSuccess() {
return success;
}

public void setSuccess(Boolean success) {
this.success = success;
}

public Integer getIduser() {
return iduser;
}

public void setIduser(Integer iduser) {
this.iduser = iduser;
}

}