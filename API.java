//Get Comments And fetch Comments//

package textsummariationapp;

import facebook4j.Comment;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.PagableList;
import facebook4j.Post;
import facebook4j.Reading;
import facebook4j.ResponseList;
import facebook4j.conf.Configuration;
import facebook4j.conf.ConfigurationBuilder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class FacebookImpl {

    public static void GetCommentsForPosts(JTextArea outdoc,JTextArea indoc) throws FacebookException {

        // Make the configuration builder 
        ConfigurationBuilder confBuilder = new ConfigurationBuilder();
        confBuilder.setDebugEnabled(true);

        // Set Application id, Secret key and Access token details
        confBuilder.setOAuthAppId("10153182943857217_10153182955262217"); // Get from developers.facebook.com by just creating a new app
        confBuilder.setOAuthAppSecret("b94d114cce76a5a5e0d00881281900bb"); // Get from developers.facebook.com by just creating a new app 
        confBuilder.setOAuthAccessToken("1187053717976174|ARNu_Mj4Muh3IZxs_J_V_fq9Lks"); // Get App Token option - Get from developers.facebook.com by just creating a new app,
        https://developers.facebook.com/tools/explorer/1187053717976174

        // Set permission 
        confBuilder.setOAuthPermissions("email,publish_stream, id, name, first_name, last_name, generic");// To retrive data from Graph API
        confBuilder.setUseSSL(true);
        confBuilder.setJSONStoreEnabled(true);

        // Create configuration object  
        Configuration configuration = confBuilder.build();

        // Create facebook instance  
        FacebookFactory ff = new FacebookFactory(configuration);
        Facebook facebook = ff.getInstance();

        try {
            if(!indoc.getText().isEmpty()){
            getFacebookPostes(facebook,indoc);
            outdoc.setText("Writing completed");
            System.out.println("Writing completed");
            }
            else{
            JOptionPane.showMessageDialog(summary.sm, "Please give page name to fetch", "Fetch exception", JOptionPane.ERROR_MESSAGE); // To display the Error Message
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block 
            e.printStackTrace(); //Prints this throwable and its backtrace to the standard error stream
        }
    }

    public static void getFacebookPostes(Facebook facebook,JTextArea indoc) throws FacebookException {
        try {
            File file = new File("Postcomments.txt");

            if (file.exists()) {
                file.delete();
                file.createNewFile();
            }
            
            

            FileWriter fileWritter = new FileWriter(file.getName(), true);
            BufferedWriter bufferWritter = new BufferedWriter(fileWritter);

            final Reading reading = new Reading().limit(10).fields("message","comments");
            ResponseList<Post> results = facebook.getPosts(indoc.getText().trim(), reading);//To get comments from selected posts in the Facebook
            for (int i = 0; i < results.size(); i++) {

                Post post = results.get(i);
                FacebookImpl.AppendTextToFile(bufferWritter, post.getMessage());
                PagableList<Comment> commenti = post.getComments();
                for (int x = 0; x < commenti.size(); x++) {
                    FacebookImpl.AppendTextToFile(bufferWritter, "   "+commenti.get(x).getMessage());
                }
                bufferWritter.newLine();
                
                if(i>10){
                    break;
                }
                
            }
            bufferWritter.close();
        } catch (IOException | FacebookException e) {
            System.out.println(e);
        }
    }

    public static void AppendTextToFile(BufferedWriter bufferWritter, String data) {
        try {
            if (data != null) {
                bufferWritter.write(data);
                bufferWritter.newLine();
            }

        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
