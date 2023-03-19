package TM01;

import Models.ResponseModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class TampilanTM01 extends JFrame implements ActionListener {
    private JTextField msgArea, stsArea, cmtArea, jmlIndex;
    private JLabel msgLabel, stsLabel, cmtLabel, jmlLabel;

    private JButton submit, close;
    TampilanTM01(){

        JFrame f = new JFrame();
        submit = new JButton("Submit");
        submit.setBounds(10,20,120,20);
        submit.addActionListener(this);
        msgLabel = new JLabel("Message");
        msgLabel.setBounds(10, 50, 100,20 );
        msgArea = new JTextField();
        msgArea.setBounds(70,50,200, 30);
        jmlLabel = new JLabel("Jumlah Index");
        jmlLabel.setBounds(320, 20, 100,20 );
        jmlIndex = new JTextField();
        jmlIndex.setBounds(320,50,100, 30);
        stsLabel = new JLabel("Status");
        stsLabel.setBounds(10, 100, 100,20 );
        stsArea = new JTextField();
        stsArea.setBounds(70,100,200, 30);
        cmtLabel = new JLabel("Comment");
        cmtLabel.setBounds(10, 160, 100,20 );
        cmtArea = new JTextField();
        cmtArea.setBounds(70,160,200, 30);
        close = new JButton("Close");
        close.setBounds(250,200,120,20);
        close.addActionListener(e -> f.setVisible(false));

        f.add(submit);
        f.add(msgLabel);
        f.add(msgArea);
        f.add(stsLabel);
        f.add(stsArea);
        f.add(cmtLabel);
        f.add(cmtArea);
        f.add(close);
        f.add(jmlIndex);
        f.add(jmlLabel);
        f.setSize(500,300);
        f.setTitle("TM01-22090121-ARDAYUDRIKMALANA-2A");

        f.setLayout(null);
        f.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            try {
                // AMBIL DATA
                URL url = new URL("https://harber.mimoapps.xyz/api/getaccess.php");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                // BACA DATA DARI JSON KE ARRAY
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // PARSE JSON
                JSONArray jsonArray = new JSONArray(response.toString());

                // CONVER JSON KE ARRAY
                ArrayList<ResponseModel> parsedList = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    ResponseModel resModel = new ResponseModel();
                    JSONObject myJSONObject = jsonArray.getJSONObject(i);
                    resModel.setMsg(myJSONObject.getString("message"));
                    resModel.setStatus(myJSONObject.getString("status"));
                    resModel.setComment(myJSONObject.getString("comment"));
                    parsedList.add(resModel);

                }
                //DIMUNCULKAN DI TEXT AREA
                for (int index = 0; index < parsedList.size();index++){
                    msgArea.setText(parsedList.get(index).getMsg());
                    stsArea.setText(parsedList.get(index).getStatus());
                    cmtArea.setText(parsedList.get(index).getComment());

                    jmlIndex.setText(String.valueOf(parsedList.get(index).getMsg().length()));
                }


            } catch (IOException ex) {

                JOptionPane.showMessageDialog(this, "Data gagal", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (JSONException ex) {

                JOptionPane.showMessageDialog(this, "Data Gagal", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }



    public static void main(String[] args) {
        new TampilanTM01();
    }
}
