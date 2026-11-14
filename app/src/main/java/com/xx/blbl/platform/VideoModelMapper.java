package com.xx.blbl.platform;

import android.text.Html;

import com.xx.blbl.model.search.SearchItemModel;
import com.xx.blbl.model.user.OwnerModel;
import com.xx.blbl.model.video.HistoryModel;
import com.xx.blbl.model.video.HistoryVideoModel;
import com.xx.blbl.model.video.VideoModel;

import java.util.ArrayList;
import java.util.Iterator;

/** Recovered application-only conversion logic; no copied SDK implementation. */
public abstract class VideoModelMapper {
    public static VideoModel fromHistory(HistoryVideoModel historyVideoModel) {
        VideoModel videoModel = new VideoModel();
        videoModel.setBvid(historyVideoModel.getBvid());
        HistoryModel history = historyVideoModel.getHistory();
        if (history != null) {
            videoModel.setEpid(history.getEpid());
            videoModel.setCid(history.getCid());
            videoModel.setAid(history.getOid());
            videoModel.setBvid(history.getBvid());
        }
        videoModel.setPic(historyVideoModel.getCover());
        videoModel.setRedirect_url(historyVideoModel.getUri());
        videoModel.setTitle(historyVideoModel.getTitle());
        videoModel.setOwner(new OwnerModel());
        OwnerModel owner = videoModel.getOwner();
        if (owner != null) {
            owner.setMid(historyVideoModel.getAuthor_mid());
        }
        OwnerModel owner2 = videoModel.getOwner();
        if (owner2 != null) {
            owner2.setFace(historyVideoModel.getAuthor_face());
        }
        OwnerModel owner3 = videoModel.getOwner();
        if (owner3 != null) {
            owner3.setName(historyVideoModel.getAuthor_name());
        }
        videoModel.setProgress(historyVideoModel.getProgress() * ((long) 1000));
        return videoModel;
    }

    public static ArrayList fromHistoryList(ArrayList arrayList) {
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            HistoryVideoModel historyVideoModel = (HistoryVideoModel) it.next();
            VideoModel videoModel = new VideoModel();
            videoModel.setBvid(historyVideoModel.getBvid());
            HistoryModel history = historyVideoModel.getHistory();
            if (history != null) {
                videoModel.setEpid(history.getEpid());
                videoModel.setCid(history.getCid());
                videoModel.setAid(history.getOid());
                videoModel.setBvid(history.getBvid());
            }
            videoModel.setPic(historyVideoModel.getCover());
            videoModel.setRedirect_url(historyVideoModel.getUri());
            videoModel.setTitle(historyVideoModel.getTitle());
            videoModel.setOwner(new OwnerModel());
            OwnerModel owner = videoModel.getOwner();
            if (owner != null) {
                owner.setMid(historyVideoModel.getAuthor_mid());
            }
            OwnerModel owner2 = videoModel.getOwner();
            if (owner2 != null) {
                owner2.setFace(historyVideoModel.getAuthor_face());
            }
            OwnerModel owner3 = videoModel.getOwner();
            if (owner3 != null) {
                owner3.setName(historyVideoModel.getAuthor_name());
            }
            videoModel.setProgress(historyVideoModel.getProgress() * ((long) 1000));
            arrayList2.add(videoModel);
        }
        return arrayList2;
    }

    public static VideoModel fromSearch(SearchItemModel searchItemModel) {
        if (searchItemModel.getRoomid() != 0) {
            searchItemModel.setArcurl("https://live.bilibili.com/" + searchItemModel.getRoomid());
            searchItemModel.setAid(searchItemModel.getRoomid());
            searchItemModel.setAuthor(searchItemModel.getUname());
        }
        VideoModel videoModel = new VideoModel();
        videoModel.setBvid(searchItemModel.getBvid());
        videoModel.setAid(searchItemModel.getAid());
        videoModel.setPic("https:" + searchItemModel.getPic());
        videoModel.setRedirect_url(searchItemModel.getArcurl());
        videoModel.setTitle(Html.fromHtml(searchItemModel.getTitle()).toString());
        videoModel.setOwner(new OwnerModel());
        OwnerModel owner = videoModel.getOwner();
        if (owner != null) {
            owner.setMid(searchItemModel.getMid());
        }
        OwnerModel owner2 = videoModel.getOwner();
        if (owner2 != null) {
            owner2.setFace(searchItemModel.getUpic());
        }
        OwnerModel owner3 = videoModel.getOwner();
        if (owner3 != null) {
            owner3.setName(searchItemModel.getAuthor());
        }
        return videoModel;
    }
}
