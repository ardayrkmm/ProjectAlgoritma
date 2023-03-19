import Models.ResponseModel;
import network.ConnectUri;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class firsConnetc {
    public static void main(String[] args) throws IOException {
        ConnectUri koneksiSaya = new ConnectUri();
        URL myAddres = koneksiSaya.buildURL(
                "https://harber.mimoapps.xyz/api/getaccess.php"
        );
        String response = koneksiSaya.getResponseFromHTTPurl(myAddres);
        System.out.println(response);

        assert response != null;
        JSONArray responseJson = new JSONArray(response);
        ArrayList<ResponseModel> responseModel = new ArrayList<>();
        for (int i = 0; i < responseJson.length(); i++){
            ResponseModel resModel = new ResponseModel();
            JSONObject myJSONObject = responseJson.getJSONObject(i);
            resModel.setMsg(myJSONObject.getString("message"));
            resModel.setStatus(myJSONObject.getString("status"));
            resModel.setComment(myJSONObject.getString("comment"));
            responseModel.add(resModel);
        }
        System.out.println("Response Are :");
        for (int index = 0; index < responseModel.size();index++){
            System.out.println("MeSSAGE : " + responseModel.get(index).getMsg());
            System.out.println("STATUS : " + responseModel.get(index).getStatus());
            System.out.println("COMMENT : " + responseModel.get(index).getComment());
        }
    }
}
