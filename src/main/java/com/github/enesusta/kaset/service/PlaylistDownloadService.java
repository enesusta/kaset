package com.github.enesusta.kaset.service;

import java.util.Set;

public interface PlaylistDownloadService {
    Set<String> getUrlsFromPlaylist(String playlistUrl);
}
