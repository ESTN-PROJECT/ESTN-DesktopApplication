/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import pidev.entities.Article;
import pidev.entities.Discussion;
import pidev.tools.MyConnection;

/**
 *
 * @author Firas
 */
public class DiscussionCRUD {
    private static Map<String, String[]> allBadWords = new HashMap<String, String[]>();

    public DiscussionCRUD() {
        
    }
    
    public void ajouterDiscussion(Discussion discussion){
        try {
            String requete= "INSERT INTO Discussion(contenu)"
                    + "VALUES (?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setString(1, discussion.getContenu());
            
            pst.executeUpdate();
            System.out.println("Discussion inserée");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void suppDisc(Discussion discussion){
        
         try {
            String requete = "UPDATE discussion SET archive=1 WHERE id=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setInt(1, discussion.getId());
            pst.executeUpdate();
            System.out.println("Discussion supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    public void updateDisscusion(Discussion discussion){
         try {
            String requete = "UPDATE discussion SET contenu=? WHERE id=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setString(1, discussion.getContenu());
            pst.setInt(2, discussion.getId());
            pst.executeUpdate();
            System.out.println("Discussion modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
       public List<Discussion> displayDiscussion() {
         List<Discussion> discussionList = new ArrayList<>();
        try {
            String requete = "SELECT * FROM discussion";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs =  st.executeQuery(requete);
            while(rs.next()){
                  if(rs.getBoolean("archive")==true){
                    
                }else{
                Discussion a = new Discussion();
                a.setId(rs.getInt("id"));
                a.setContenu(rs.getString("contenu"));
                             
                discussionList.add(a);
            }}
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return discussionList;
}
        public String Filter(Discussion d){
         int n=0;
                 
         String description =d.getContenu();
        
        int index = description.indexOf("bhim");
       
        if(index!=-1){
            String str=description;
            for(int i=0;i<4;i++){
                str=replaceChar(str, '*', index+i);
            }
            d.setContenu(str);
                        updateDisscusion(d);
        n+=1;
            return str;
        }
            
        return description;
    } 
          public String replaceChar(String str, char ch, int index) {
        return str.substring(0, index) + ch + str.substring(index+1);
    }
          private static int largestWordLength = 0;

  

  /**
   * Iterates over a String input and checks whether any cuss word was found - and for any/all cuss word found, 
   * as long as the cuss word should not be ignored (i.e. check for false positives - e.g. even though "bass" 
   * contains the word *ss, bass should not be censored) then (in the String returned) replace the cuss word with asterisks.
   */
  public static String getCensoredText(final String input) {
      loadBadWords();
    if (input == null) {
      return "";
    }

    String modifiedInput = input;

    // remove leetspeak
    modifiedInput = modifiedInput.replaceAll("1", "i").replaceAll("!", "i").replaceAll("3", "e").replaceAll("4", "a")
        .replaceAll("@", "a").replaceAll("5", "s").replaceAll("7", "t").replaceAll("0", "o").replaceAll("9", "g");

    // ignore any character that is not a letter
    modifiedInput = modifiedInput.toLowerCase().replaceAll("[^a-zA-Z]", "");

    ArrayList<String> badWordsFound = new ArrayList<>();

    // iterate over each letter in the word
    for (int start = 0; start < modifiedInput.length(); start++) {
      // from each letter, keep going to find bad words until either the end of
      // the sentence is reached, or the max word length is reached.
      for (int offset = 1; offset < (modifiedInput.length() + 1 - start) && offset < largestWordLength; offset++) {
        String wordToCheck = modifiedInput.substring(start, start + offset);
        if (allBadWords.containsKey(wordToCheck)) {
          String[] ignoreCheck = allBadWords.get(wordToCheck);
          boolean ignore = false;
          for (int stringIndex = 0; stringIndex < ignoreCheck.length; stringIndex++) {
            if (modifiedInput.contains(ignoreCheck[stringIndex])) {
              ignore = true;
              break;
            }
          }

          if (!ignore) {
            badWordsFound.add(wordToCheck);
          }
        }
      }
    }

    String inputToReturn = input;
    for (String swearWord : badWordsFound) {
      char[] charsStars = new char[swearWord.length()];
      Arrays.fill(charsStars, '*');
      final String stars = new String(charsStars);

      // The "(?i)" is to make the replacement case insensitive.
      inputToReturn = inputToReturn.replaceAll("(?i)" + swearWord, stars);
    }

    return inputToReturn;
  } // end getCensoredText

  private static void loadBadWords() {
    int readCounter = 0;
    try {
      // The following spreadsheet is from: https://gist.github.com/PimDeWitte/c04cc17bc5fa9d7e3aee6670d4105941
      // (If the spreadsheet ever ceases to exist, then this application will still function normally otherwise - it just won't censor any swear words.)

             FileReader fr = new FileReader("E:\\Nouveau dossier (2)\\file.csv");
             BufferedReader reader = new BufferedReader(fr);

//      BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(
//          "https://docs.google.com/spreadsheets/d/1hIEi2YG3ydav1E06Bzf2mQbGZ12kh2fe4ISgLg_UBuM/export?format=csv")
//          .openConnection().getInputStream()));


      String currentLine = "";
      while ((currentLine = reader.readLine()) != null) {
        readCounter++;
        String[] content = null;
        try {
          if (1 == readCounter) {
            continue;
          }

          content = currentLine.split(",");
          if (content.length == 0) {
            continue;
          }

          final String word = content[0];

          if (word.startsWith("-----")) {
            continue;
          }

          if (word.length() > largestWordLength) {
            largestWordLength = word.length();
          }

          String[] ignore_in_combination_with_words = new String[] {};
          if (content.length > 1) {
            ignore_in_combination_with_words = content[1].split("_");
          }

          // Make sure there are no capital letters in the spreadsheet
          allBadWords.put(word.replaceAll(" ", "").toLowerCase(), ignore_in_combination_with_words);
        } catch (Exception except) {
        }
      } // end while
    } catch (IOException except) {
    }
  } // end loadBadWords

}

