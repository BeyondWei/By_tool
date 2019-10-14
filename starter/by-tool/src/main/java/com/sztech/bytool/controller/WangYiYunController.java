package com.sztech.bytool.controller;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Objects;

@RestController
@RequestMapping("wang")
public class WangYiYunController {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 获取网易云歌单中的歌曲ID
     *
     * @author 无垠
     * @Date 2019/9/19 16:06
     */
    @RequestMapping("getPlaylist")
    public HashMap<String, String> getPlaylist(String id) throws IOException {
        HashMap<String, String> song = new HashMap<>();
        for (Element w : Jsoup.connect("http://music.163.com/playlist?id=" + id)
                .header("Referer", "http://music.163.com/")
                .header("Host", "music.163.com").get().select("ul[class=f-hide] a")) {
            String str = w.attr("href");
            if (!StringUtils.isEmpty(str)) {
                song.put(str.split("=")[1], w.text());
            }
        }
        return song;
    }

    /**
     * 歌单下载
     *
     * @author 无垠
     * @Date 2019/9/19 16:08
     */
    @RequestMapping("downloadPlaylist")
    public String downloadPlaylist(String id, String filePath) throws IOException {
        if (StringUtils.isEmpty(filePath)) {
            filePath = "/mnt/bytool/";
        }
        HashMap<String, String> playlist = getPlaylist(id);
        for (String key : playlist.keySet()) {
            String url = "http://music.163.com/song/media/outer/url?id=";
            url += key;
            ResponseEntity<byte[]> rsp = restTemplate.getForEntity(url, byte[].class);
            File file = new File(filePath);
            if (!file.exists()) {
                file.mkdirs();
            }
            String name = playlist.get(key);
            String desktopPath = file.getAbsolutePath() + "\\" + name + ".mp3";
            Files.write(Paths.get(desktopPath), Objects.requireNonNull(rsp.getBody(), "未获取到下载文件"));
            System.out.println(desktopPath);
        }
        return filePath;
    }

    /**
     * 单个歌曲下载
     *
     * @author 无垠
     * @Date 2019/9/19 17:42
     */
    @RequestMapping("downloadSong")
    public String downloadSong(String id, String filePath, String name) throws IOException {
        if (StringUtils.isEmpty(filePath)) {
            filePath = "/mnt/bytool/";
        }
        String url = "http://music.163.com/song/media/outer/url?id=" + id;
        ResponseEntity<byte[]> rsp = restTemplate.getForEntity(url, byte[].class);
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        String desktopPath = file.getAbsolutePath() + "\\" + name + ".mp3";
        Files.write(Paths.get(desktopPath), Objects.requireNonNull(rsp.getBody(), "未获取到下载文件"));
        System.out.println(desktopPath);
        return filePath;
    }

}
