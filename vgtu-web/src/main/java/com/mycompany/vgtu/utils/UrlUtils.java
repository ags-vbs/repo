package com.mycompany.vgtu.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UrlUtils {

    private static final String YOUTUBE_VIDEO_PARAM = "v";
    private static final String YOUTUBE_EMBED_VIDEO_CONSTANT = "//www.youtube.com/embed/";
    private static final String YOUTUBE_SHARE_URL_CONSTANT = "http://youtu.be/";

    //TODO improve this method. It is very primitive one.
    public static boolean isYoutubeUrlValid(String simpleVideoUrl) {
        if (simpleVideoUrl.contains(YOUTUBE_SHARE_URL_CONSTANT)) {
            String[] strings = simpleVideoUrl.split(YOUTUBE_SHARE_URL_CONSTANT);
            if (strings.length == 2) {
                return true;
            } else {
                return false;
            }
        } else {
            Map<String, List<String>> mapOfUrlParams = getQueryParams(simpleVideoUrl);
            if (mapOfUrlParams.containsKey(YOUTUBE_VIDEO_PARAM)) {
                return true;
            } else {
                return false;
            }
        }
    }

    //TODO improve this method. It is very primitive one.
    public static String getYoutubeEmbedUrlFrom(String simpleVideoUrl) {
        if (simpleVideoUrl.contains(YOUTUBE_SHARE_URL_CONSTANT)) {
            String[] strings = simpleVideoUrl.split(YOUTUBE_SHARE_URL_CONSTANT);
            if (strings.length != 2) {
                throw new IllegalArgumentException("Video url format is incorect");
            } else {
                return YOUTUBE_EMBED_VIDEO_CONSTANT + strings[1];
            }
        } else {
            Map<String, List<String>> mapOfUrlParams = getQueryParams(simpleVideoUrl);
            if (mapOfUrlParams.containsKey(YOUTUBE_VIDEO_PARAM)) {
                String fullIframeSrcUrl = YOUTUBE_EMBED_VIDEO_CONSTANT + mapOfUrlParams.get(YOUTUBE_VIDEO_PARAM).get(0);
                return fullIframeSrcUrl;
            } else {
                throw new IllegalArgumentException("Video url format is incorect");
            }
        }
    }

    public static Map<String, List<String>> getQueryParams(String url) {
        try {
            Map<String, List<String>> params = new HashMap<String, List<String>>();
            String[] urlParts = url.split("\\?");
            if (urlParts.length > 1) {
                String query = urlParts[1];
                for (String param : query.split("&")) {
                    String[] pair = param.split("=");
                    String key = URLDecoder.decode(pair[0], "UTF-8");
                    String value = "";
                    if (pair.length > 1) {
                        value = URLDecoder.decode(pair[1], "UTF-8");
                    }

                    List<String> values = params.get(key);
                    if (values == null) {
                        values = new ArrayList<String>();
                        params.put(key, values);
                    }
                    values.add(value);
                }
            }

            return params;
        } catch (UnsupportedEncodingException ex) {
            throw new AssertionError(ex);
        }
    }
}
