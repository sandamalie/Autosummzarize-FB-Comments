/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textsummariationapp;

import facebook4j.FacebookException;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.Enumeration;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class summary extends JFrame implements ActionListener {

    JTextArea indoc, outdoc;
    ArrayList als;
    File fp;
    Hashtable hs;
    double scnt;
    public static summary sm;

    public summary() {

        Container con = getContentPane();
        con.setLayout(new BorderLayout());
        con.add(addToolbar(), BorderLayout.NORTH);
        con.add(addwinSplit(), BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Automatic Text Summarization For Social Network Comments");// Set the title of the application
        setSize(800, 600);
        setVisible(true);
        als = new ArrayList();
        hs = new Hashtable();
    }

    public JPanel addToolbar() {
        JToolBar jtb = new JToolBar();

        JButton jb10 = new JButton("Fetch Comments");
        jb10.addActionListener(this);// Add action listener to the button
        jb10.setForeground(new Color(200, 100, 150));
        jtb.add(jb10);

        JButton jb11 = new JButton("Preprocessing Comments");//To remove unwanted spaces and specific symbols
        jb11.addActionListener(this);
        jb11.setForeground(new Color(200, 100, 150));
        jtb.add(jb11);

        JButton jb4 = new JButton("Upload Comments");//To uplaod the Preprocessed Comments textfile
        jb4.addActionListener(this);
        jb4.setForeground(new Color(200, 100, 150));
        jtb.add(jb4);

        JButton jb1 = new JButton("Sentense");
        jb1.addActionListener(this);
        jb1.setForeground(new Color(200, 100, 150));
        jtb.add(jb1);

      //  JButton jb2 = new JButton("Stopword");//we can focus on the important words instead of the stop words
      //  jb2.addActionListener(this);
      //  jb2.setForeground(new Color(200, 100, 150));
      //  jtb.add(jb2);

//        JButton jb3 = new JButton("Unique word");//To identify the similarity of the words and remove them if we don't want them
//        jb3.addActionListener(this);
//        jb3.setForeground(new Color(200, 100, 150));
//        jtb.add(jb3);
//
//        JButton jb5 = new JButton("Stemming");
//        jb5.addActionListener(this);
//        jb5.setForeground(new Color(200, 100, 150));
//       jtb.add(jb5); //Have to remove comments
//
//        JButton jb6 = new JButton("Significant");//To idemtify the most usable or important words from the given comments text file
//        jb6.addActionListener(this);
//        jb6.setForeground(new Color(200, 100, 150));
//     jtb.add(jb6); //Have to remove comments
//
//        JButton jb7 = new JButton("Weight");
//        jb7.addActionListener(this);
//        jb7.setForeground(new Color(200, 100, 150));
//        jtb.add(jb7); //Have to remove comments
//
//        JButton jb8 = new JButton("Ranking");//Based on the weight ranking function happend
//        jb8.addActionListener(this);
//        jb8.setForeground(new Color(200, 100, 150));
//	jtb.add(jb8);  //Have to remove comments

       // JButton jb9 = new JButton("Summary");
      //  jb9.addActionListener(this);
      //  jb9.setForeground(new Color(200, 100, 150));
	//jtb.add(jb9); //Have to remove comments

        JPanel jp = new JPanel();
        jp.setLayout(new GridLayout(1, 1));
        jp.add(jtb);
        return jp;
    }

    public JSplitPane addwinSplit() {
        JSplitPane jsp = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        jsp.setDividerLocation(350);
        jsp.setOneTouchExpandable(true);

        indoc = new JTextArea(20, 60);
        indoc.setFont(new Font("New Times Roman", Font.PLAIN, 15));
        JScrollPane js1 = new JScrollPane(indoc);

        outdoc = new JTextArea(20, 60);
        outdoc.setFont(new Font("New Times Roman", Font.PLAIN, 15));
        JScrollPane js2 = new JScrollPane(outdoc);

        jsp.setTopComponent(js1);
        jsp.setBottomComponent(js2);
        return jsp;
    }

    public void setdocument(File fp) {

        try {
            int sz = (int) fp.length();
            byte bs[] = new byte[sz];
            FileInputStream fis = new FileInputStream(fp);
            fis.read(bs);
            indoc.setText(new String(bs));
            fis.close();
        } catch (IOException ex) {
        }
    }

    public void removestopwords() {//This method used to remove stop words and special charatcers as well

        Stopword sp = new Stopword();//Look for the stop words java file 
        Special spl = new Special(); //look for special characters java file 
        for (int i = 0; i < als.size(); i++) {
            slist sl = (slist) als.get(i);
            sl.setsrsentence(spl.remove(sl.getrawsentense())); //Remove the Special character for the sentence
            sl.setsrsentence(sp.remove(sl.getsrsentence()));//Remove the Stopword for the sentence
        }

        outdoc.setText("");//Display output textarea
        outdoc.setText("NO OF SENTENSE :" + als.size() + "\n\n");//To display the sentences once removes for stop words and special characters
        for (int i = 0; i < als.size(); i++) {
            slist sl = (slist) als.get(i);
            outdoc.setText(outdoc.getText() + "\n" + (i + 1) + ":  " + sl.getsrsentence());//To display sentences with numbers
        }

    }

    public void separatesentense(File fp) {
        Sentense sc = new Sentense(fp);
        sc.separatesentense(als);
        outdoc.setText("");
        outdoc.setText("NO OF SENTENSE :" + als.size() + "\n\n");
        scnt = als.size();//Returns the number of elements in this list.
        for (int i = 0; i < als.size(); i++) {
            slist sl = (slist) als.get(i);
            outdoc.setText(outdoc.getText() + "\n " + (i + 1) + ":  " + sl.getrawsentense());
        }
    }

    public double difpos(String str1, String str2) {
        int s1 = str1.length();
        int s2 = str2.length();
        int sz = (s1 > s2) ? s1 : s2;
        int mz = (s1 < s2) ? s1 : s2;
        double dp = mz, sm = 0;
        for (int i = 0; i < mz; i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                dp = i + 1;
                break;
            } else {
                sm++;
            }
        }
        return (sm * (dp / sz));
    }

    private void addword(String tok, int sp, int wp) {
        wlist wl = new wlist(tok);
        wl.incrcount(sp + 1, wp);
        hs.put(tok, wl);
    }

    private void upword(String tok, int sp, int wp) {
        wlist wl = (wlist) hs.remove(tok);
        wl.incrcount(sp + 1, wp);
        hs.put(tok, wl);
    }

    private void delword(Object tok) {
        wlist wl = (wlist) hs.remove(tok);
        if (wl.getcount() > 3) {
            hs.put(tok, wl);
        }
    }

    private void setwight(Object tok) { // Calculate the weight of the sentence  tok - Selected word in the sentence(Give count of total sentence)
        double wg = 0.0;
        wlist wl = (wlist) hs.get(tok);//Create the object reference of the Wlist.java class
        double tf = wl.getcount(); //Get the particular word count
        double df = wl.sentensecount();//Get the count of  sentences (one word references to several sentences,so weight counts that word with different sentences
        wg = tf * Math.log10(scnt / df);  //scnt - Total Sentence Count, df (Document frequency) - Specific work how many sentence count
        wl.weight(wg);
    }

    public void Uniquewords() {
        for (int i = 0; i < als.size(); i++) {
            slist sl = (slist) als.get(i);
            String sen = sl.getsrsentence();
            int wc = 0;
            StringTokenizer stk = new StringTokenizer(sen, " ");
            while (stk.hasMoreElements()) {
                String tok = (String) stk.nextElement();
                tok = tok.trim();
                wc++;
                if (!hs.containsKey(tok) && tok.length() >= 3) { //minimum stem length = '3'
                    addword(tok, i, wc);
                } else if (hs.containsKey(tok)) { //Checks if the hashMap contains particular key.
                    upword(tok, i, wc);
                }
            }
        }

        outdoc.setText("No of unique words:" + hs.size() + "\n");
        Enumeration key = hs.keys();
        while (key.hasMoreElements()) {
            wlist wl = (wlist) hs.get(key.nextElement());
            outdoc.setText(outdoc.getText() + "\n" + wl.getword() + ":  " + wl.getcount());
        }
    }

    public void stemword(String w1, String w2) {

        if (!hs.containsKey(w2) || !hs.containsKey(w1)) { //Checks if the hashMap morpological related words(Example: Great,Greati.)
            // System.out.print("return:"); //used to check if this map contains a mapping for the specified key
            return;
        }
        wlist wl1 = (wlist) hs.remove(w1);
        wlist wl2 = (wlist) hs.remove(w2);

        ArrayList wp = wl2.getwordpos();//To get the Word position
        ArrayList sp = wl2.getsentensepos();//To get the Sentence position

        for (int i = 0; i < wp.size(); i++) {
            String wp2 = (String) wp.get(i);
            String sp2 = (String) sp.get(i);
            wl1.incrcount(Integer.parseInt(wp2), Integer.parseInt(sp2));
        }

        hs.put(w1, wl1);

    }

    public void stemming() {

        int sz = hs.size();
        double wdis[][] = new double[sz][sz];
        Set s1 = hs.keySet();
        Object obj[] = s1.toArray();

        for (int i = 0; i < sz; i++) {
            String str1 = (String) obj[i];
            for (int j = 0; j < sz; j++) {
                String str2 = (String) obj[j];
                if (i != j) {
                    wdis[i][j] = difpos(str1, str2);
                }
            }
        }

        for (int i = 0; i < sz; i++) {
            String str1 = (String) obj[i];
            for (int j = 0; j < sz; j++) {
                String str2 = (String) obj[j];
                if (i != j && wdis[i][j] >= 3.0) {
                    stemword(str1, str2);
                }
            }
        }

        outdoc.setText("No of unique words:" + hs.size() + "\n");
        Enumeration key = hs.keys();  // Generates the list of elements	    
        while (key.hasMoreElements()) {
            wlist wl = (wlist) hs.get(key.nextElement());
            outdoc.setText(outdoc.getText() + "\n" + wl.getword() + ":  " + wl.getcount());
        }
    }

    public void significant() {

        Enumeration key = hs.keys(); //Enumeration interface generates a series of elements, one at a time
        while (key.hasMoreElements()) { //now duplicated by java.util.Iterator - relevant meaning of the word
            delword(key.nextElement()); //hasMoreElements returns true as long as there are still more objects in the container 
        }

        outdoc.setText("No of Significant words:" + hs.size() + "\n");
        key = hs.keys();
        while (key.hasMoreElements()) { //Need to be a root of the tree which is at the top of the stack
            wlist wl = (wlist) hs.get(key.nextElement());
            outdoc.setText(outdoc.getText() + "\n" + wl.getword() + ":  " + wl.getcount() + ":" + wl.sentensecount());
        }
    }

    public void weight() {
        Enumeration key = hs.keys();
        while (key.hasMoreElements()) {
            setwight(key.nextElement());
        }

        outdoc.setText("Weight of Significant words:" + "\n");
        key = hs.keys();
        while (key.hasMoreElements()) {
            wlist wl = (wlist) hs.get(key.nextElement());
            outdoc.setText(outdoc.getText() + "\n" + wl.getword() + ":  " + wl.weight());
        }
    }

    public String ranking() {
        slist sl = null;
        double max = 0.0;
        int mi = 0;

        for (int i = 0; i < als.size(); i++) {
            sl = (slist) als.get(i);
            String sen = sl.getsrsentence();
            Enumeration key = hs.keys();
            while (key.hasMoreElements()) {
                String str = (String) key.nextElement();
                if (sen.indexOf(str) != -1) {
                    wlist wl = (wlist) hs.get(str);
                    sl.weight(wl.weight());
                }
            }
        }

        for (int i = 0; i < als.size(); i++) {
            sl = (slist) als.get(i);
            if (sl.weight() > max) {
                max = sl.weight();
                mi = i;
            }
        }

  //      outdoc.setText("\t SUMMARY \n");
        String str1 = sl.getrawsentense();
        sl = (slist) als.get(mi);
        
        outdoc.setText("");
    //   outdoc.setText(outdoc.getText() + sl.getrawsentense() + "\n");
        outdoc.setText("\n Sentense  and Ranking " + "\n");

        for (int i = 0; i < als.size(); i++) {
            sl = (slist) als.get(i);
           outdoc.setText(outdoc.getText() + "\n" + (i + 1) + " : " + sl.getrawsentense() + " : " + sl.weight());
        }

        return str1;
    }

    public void summarize() {

        separatesentense(fp);
        removestopwords();
        Uniquewords();
        stemming();
        significant();
        weight();
        //String str = ranking();
        slist sl = null;
        double max = 0.0;
        int mi = 0;

        for (int i = 0; i < als.size(); i++) {
            sl = (slist) als.get(i);
            String sen = sl.getsrsentence();
            Enumeration key = hs.keys();
            while (key.hasMoreElements()) {
                String str = (String) key.nextElement();
                if (sen.indexOf(str) != -1) {
                    wlist wl = (wlist) hs.get(str);
                    sl.weight(wl.weight());
                }
            }
        }

        for (int i = 0; i < als.size(); i++) {
            sl = (slist) als.get(i);
            if (sl.weight() > max) {
                max = sl.weight();
                mi = i;
            }
        }

        outdoc.setText("\t SUMMARY \n");
        String str1 = sl.getrawsentense();
        sl = (slist) als.get(mi);
        outdoc.setText(outdoc.getText() + sl.getrawsentense() + "\n");

//        outdoc.setText("SUMMARY \n\n");
//        outdoc.setText(outdoc.getText() + str);

    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("Upload Comments")) {
            JFileChooser fc = new JFileChooser();
            if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                als.clear();
                hs.clear();
                fp = fc.getSelectedFile();
                setdocument(fp);
            }
        } else if (ae.getActionCommand().equals("Sentense")) {
            separatesentense(fp);
        } else if (ae.getActionCommand().equals("Stopword")) {
            removestopwords();
        } else if (ae.getActionCommand().equals("Unique word")) {
            Uniquewords();
        } else if (ae.getActionCommand().equals("Stemming")) {
            stemming();
        } else if (ae.getActionCommand().equals("Significant")) {
            significant();
        } else if (ae.getActionCommand().equals("Weight")) {
            weight();
        } else if (ae.getActionCommand().equals("Ranking")) {
            ranking();
        } else if (ae.getActionCommand().equals("Summary")) {
            summarize();
        } else if (ae.getActionCommand().equals("Fetch Comments")) {
            try {
                FacebookImpl.GetCommentsForPosts(outdoc, indoc);
            } catch (FacebookException ex) {
                Logger.getLogger(summary.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (ae.getActionCommand().equals("Preprocessing Comments")) {
            this.PreprocessingFile();
        }

    }

    public static void main(String str[]) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        sm = new summary();

    }

    private void PreprocessingFile() {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try {
            String data = " This content will append to the end of the file";

            File pfile = new File("PostcommentsPreprocessed.txt");

            //if file doesnt exists, then create it
            if (pfile.exists()) {
                pfile.delete();
            }

            pfile.createNewFile();
            //true = append file
            FileWriter fileWritter = new FileWriter(pfile.getName(), true);
            BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
            File file = new File("Postcomments.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                RemoveUnwantedCharahters(line, bufferWritter);
				//stringBuffer.append(line);
                //stringBuffer.append("\n");
            }
            fileReader.close();
            bufferWritter.close();
            System.out.println("Contents of file:");
            System.out.println(stringBuffer.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void RemoveUnwantedCharahters(String line, BufferedWriter bufferWritter) {
        String temp = line.replaceAll("[^a-zA-Z\\s\\.\\,0-9]", " "); // Remove unwanted special characters from the given sentence
        try {
            bufferWritter.write(temp + "\n");
           // System.out.println("Done");

        } catch (IOException e) {
            e.printStackTrace();
        }
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
