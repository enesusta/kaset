package com.github.enesusta.kaset.repository;

import java.util.Set;

public interface PlaylistDownloadRepository {
    Set<String> getUrlsFromPlaylist(String playlistUrl);
}
