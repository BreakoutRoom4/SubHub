package com.example.subhub;

import android.media.Image;

public class LSVideo {
    private String livestreamTitle;
    private String channelName;
    private String livestreamDescription;
  //  private Image thumbnail;
    private String currentWatchers;

    public LSVideo() {
    }

    public LSVideo(String lsTitle, String chnlName, String lsDesc, String crntWatcher/*, Image tn*/){
        livestreamTitle = lsTitle;
        channelName = chnlName;
        livestreamDescription = lsDesc;
        currentWatchers = crntWatcher;
 //       thumbnail = tn;
    }

    public String getLivestreamTitle() {
        return livestreamTitle;
    }

    public void setLivestreamTitle(String livestreamTitle) {
        this.livestreamTitle = livestreamTitle;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getLivestreamDescription() {
        return livestreamDescription;
    }

    public void setLivestreamDescription(String livestreamDescription) {
        this.livestreamDescription = livestreamDescription;
    }

    public String getCurrentWatchers() {
        return currentWatchers;
    }

    public void setCurrentWatchers(String currentWatchers) {
        this.currentWatchers = currentWatchers;
    }

 /*   public Image getThumbnail(){
        return thumbnail;
    }

    public void setThumbnail(Image thumbnail)
    {
        this.thumbnail = thumbnail;
    }*/
}
