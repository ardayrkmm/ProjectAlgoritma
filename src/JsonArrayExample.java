import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonArrayExample extends JFrame {

    private JPanel contentPane;
    private JTextArea textArea;
    private JButton btnConvert;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    JsonArrayExample frame = new JsonArrayExample();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public JsonArrayExample() {
        setTitle("JSON to Array Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);

        textArea = new JTextArea();
        scrollPane.setViewportView(textArea);

        btnConvert = new JButton("Convert to Array");
        btnConvert.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                convertToJsonArray();
            }
        });
        contentPane.add(btnConvert, BorderLayout.SOUTH);
    }

    private void convertToJsonArray() {
        String jsonString = textArea.getText();
        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            String[] array = new String[jsonArray.length()];
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                String value = obj.getString("name");
                array[i] = value;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}