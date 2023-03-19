package Models;

import java.awt.*;

public class ResponseModel {
    String msg;
    String Status;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    String Comment;

    public ResponseModel(){}

    ResponseModel(String Msg, String status, String cmnt){
        this.msg = Msg;
        this.Comment = cmnt;
        this.Status = status;

    }


}
