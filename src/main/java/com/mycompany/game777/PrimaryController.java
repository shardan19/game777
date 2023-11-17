package com.mycompany.game777;

import java.io.IOException;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.binding.DoubleBinding;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import java.util.Random;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.CacheHint;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.stage.StageStyle;

public class PrimaryController {
    
    @FXML
    private AnchorPane mainAnchorPane;
    @FXML
    private Pane firstPane;
    @FXML
    private Pane secondPane;
    @FXML
    private Pane thirdPane;
    @FXML
    private Label moneyLabel;
    @FXML
    private Label lastWinLabel;
    @FXML
    private Label betLabel;
    
    private int counter=0;
    private List<ImageView> imageViews1 = new ArrayList<>();
    private List<ImageView> imageViews2 = new ArrayList<>();
    private List<ImageView> imageViews3 = new ArrayList<>();
    
    private int[][] reelsDefinition = new int[][]{
/* reel 1 */  new int[]{0, 4, 0, 0, 0, 2, 2, 3, 1, 4, 0, 6, 0, 1, 0, 0, 5, 1, 1, 3},
/* reel 2 */  new int[]{2, 5, 1, 2, 0, 1, 3, 5, 2, 6, 1, 0, 0, 0, 1, 3, 4, 4, 0, 4},
/* reel 3 */  new int[]{5, 2, 1, 0, 0, 3, 4, 0, 2, 6, 0, 2, 0, 0, 0, 1, 1, 3, 0, 4}
};

    
    @FXML
    public void initialize(){
        firstPane.toBack();
        secondPane.toBack();
        thirdPane.toBack();
        
        int[] numbers1 = getRandomAndNextThreeFromReel(1,1);
        int[] numbers2 = getRandomAndNextThreeFromReel(2,1);
        int[] numbers3 = getRandomAndNextThreeFromReel(3,1);

        System.out.println("wylosowane liczby:"+numbers1[0]+numbers1[1]+numbers1[2]);
        System.out.println("wylosowane liczby:"+numbers2[0]+numbers2[1]+numbers2[2]);
        System.out.println("wylosowane liczby:"+numbers3[0]+numbers3[1]+numbers3[2]);
 
        moveImages(numbers1,firstPane,imageViews1,345);
        moveImages(numbers2,secondPane,imageViews2,345);
        moveImages(numbers3,thirdPane,imageViews3,345);
     
        
        //System.out.println(currentY);
        
    }
    private void moveImages(int[]numbers, Pane pane,List<ImageView> imageViews,double currentTranslateY){

        for(int number:numbers){
            ImageView imageView = createImageView(number);
            imageView.setPreserveRatio(true);
            imageView.fitWidthProperty().bind(pane.widthProperty());
            imageView.setTranslateY(currentTranslateY);
            currentTranslateY -= 115;
            pane.getChildren().add(imageView);
            imageViews.add(imageView);
        }
    }
    public int[] getRandomAndNextThreeFromReel(int reelNumber,int additional) {
        Random random = new Random();

    int[] selectedReel = reelsDefinition[reelNumber - 1];
    int randomIndex = random.nextInt(selectedReel.length);
    
    int[] result = new int[3+additional];
    for (int i = 0; i < additional; i++) {
        result[i] = selectedReel[(randomIndex + i) % selectedReel.length];
    }
    for (int i = 0; i < 3; i++) {
        result[i+additional] = selectedReel[(randomIndex + i) % selectedReel.length];
    }
    
    return result;
}
    private ImageView createImageView(int number) {
        Image image = null;
        ImageView imageView = null;
        switch (number){
            case 0:
                image = new Image(getClass().getResourceAsStream("fruits/cherry.png"));
                imageView = new ImageView(image);
                break;
            case 1:
                image = new Image(getClass().getResourceAsStream("fruits/plum.png"));
                imageView = new ImageView(image);
                break;
            case 2:
                image = new Image(getClass().getResourceAsStream("fruits/orange.png"));
                imageView = new ImageView(image);
                break;
            case 3:
                image = new Image(getClass().getResourceAsStream("fruits/pineaple.png"));
                imageView = new ImageView(image);
                break;
            case 4:
                image = new Image(getClass().getResourceAsStream("fruits/rasberry.png"));
                imageView = new ImageView(image);
                break;
            case 5:
                image = new Image(getClass().getResourceAsStream("fruits/watermelon.png"));
                imageView = new ImageView(image);
                break;
            case 6:
                image = new Image(getClass().getResourceAsStream("fruits/777.png"));
                imageView = new ImageView(image);
                break;
        }
        imageView.setPreserveRatio(true);
        imageView.fitWidthProperty().bind(firstPane.widthProperty());
        //imageView1.fitHeightProperty().bind(imageView1.fitWidthProperty().divide(image1.getWidth() / image1.getHeight()));

        return imageView;
    }

    private void adjustImageViewPositions(List<ImageView> imageViews) {
        double totalHeight = 0;
        for (ImageView imageView : imageViews) {
            totalHeight += imageView.getBoundsInLocal().getHeight();
        }

        double currentY = firstPane.getHeight() - totalHeight;
        for (ImageView imageView : imageViews) {
            imageView.setY(currentY);
            currentY += imageView.getBoundsInLocal().getHeight();
        }
    }
    private int getMultipler(int number){
        int multipler =0;
        switch (number){
            case 0:
                multipler= 5;
                break;
            case 1:
                 multipler= 10;
                break;
            case 2:
                 multipler= 15;
                break;
            case 3:
                 multipler= 20;
                break;
            case 4:
                 multipler= 30;
                break;
            case 5:
                 multipler= 100;
                break;
            case 6:
                 multipler= 200;
                break;
        }
        return multipler;
    }
    @FXML
    private void addButton(){
        TextInputDialog textInput=new TextInputDialog();
        textInput.setTitle("Add Money");
        textInput.setHeaderText(null);
        textInput.setGraphic(null);
        textInput.getDialogPane().setContentText("Add Money");
        Optional<String> result = textInput.showAndWait();
        result.ifPresent(value -> {
        try {
            int enteredValue = Integer.parseInt(value);
            String money = moneyLabel.getText();
            int moneyNumber = Integer.parseInt(money);
     
            moneyNumber=moneyNumber+enteredValue;
            moneyLabel.setText(Integer.toString(moneyNumber));
        } catch (NumberFormatException e) {
            System.out.println("Wprowadzona wartość nie jest liczbą całkowitą.");
        }
        });
        

    }
    @FXML
    private void startButton(){
    counter+=1;
    System.out.println("Losuje...");
     String bet = betLabel.getText();
     int betNumber = Integer.parseInt(bet);
     String money = moneyLabel.getText();
     int moneyNumber = Integer.parseInt(money);
     
     moneyNumber=moneyNumber-betNumber;
     moneyLabel.setText(Integer.toString(moneyNumber));
     
     int[] numbers1 = getRandomAndNextThreeFromReel(1,3);
     int[] numbers2 = getRandomAndNextThreeFromReel(2,6);
     int[] numbers3 = getRandomAndNextThreeFromReel(3,9);
     System.out.println("środek to:"+numbers1[numbers1.length-2]+numbers2[numbers2.length-2]+numbers3[numbers3.length-2]);
     
     if (numbers1[numbers1.length-2]==numbers2[numbers2.length-2] && numbers2[numbers2.length-2]==numbers3[numbers3.length-2]){
         System.out.println("WYGRANA");
         int multipler=getMultipler(numbers1[numbers1.length-2]);
         moneyNumber = moneyNumber+(betNumber*multipler);
         moneyLabel.setText(Integer.toString(moneyNumber));
         lastWinLabel.setText(Integer.toString(betNumber*multipler));
     }
     
      moveImages(numbers1,firstPane,imageViews1,-115);
      moveImages(numbers2,secondPane,imageViews2,-115);
      moveImages(numbers3,thirdPane,imageViews3,-115);
      
      
      Timeline timeline1=animateImagesDown(imageViews1,690,2);
      timeline1.setOnFinished(event->{
          
          firstPane.getChildren().removeAll(imageViews1.subList(0, 6));
          imageViews1.subList(0, 6).clear();
          
      });
      timeline1.play();
      
      Timeline timeline2=animateImagesDown(imageViews2,1035,3);
      timeline2.setOnFinished(event->{
          
          secondPane.getChildren().removeAll(imageViews2.subList(0, 9));
          imageViews2.subList(0, 9).clear();
          
      });
      timeline2.play();

      Timeline timeline3=animateImagesDown(imageViews3,1380,4);
      timeline3.setOnFinished(event->{
          
          thirdPane.getChildren().removeAll(imageViews3.subList(0, 12));
          imageViews3.subList(0, 12).clear();
      });
      timeline3.play();
    }
        
    private Timeline animateImagesDown(List<ImageView> imageViews, double distance, double dur) {
    double upDistance = -50;
    double downDistance = distance + (-1 * upDistance); // dystans przesunięcia w dół

    Timeline timeline = new Timeline();

    for (ImageView imageView : imageViews) {
        imageView.setCache(true);
        imageView.setCacheHint(CacheHint.SPEED);

        double startY = imageView.getTranslateY();
        double endY = startY + upDistance + downDistance;

        imageView.translateYProperty().bind(
                Bindings.createDoubleBinding(
                        () -> {
                            double currentTime = timeline.getCurrentTime().toSeconds();
                            if (currentTime < 0.5) {
                                return startY + (upDistance * (currentTime / 0.5));
                            } else {
                                double progress = (currentTime - 0.5) / (dur - 0.5);
                                return startY + upDistance + (downDistance * progress);
                            }
                        },
                        timeline.currentTimeProperty()
                )
        );
    }

    timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(dur)));
    return timeline; 
}
}
