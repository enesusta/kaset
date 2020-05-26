package com.github.enesusta.kaset.repository;

import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public class DefaultPlaylistDownloadRepository implements PlaylistDownloadRepository {

    @Override
    public Set<String> getUrlsFromPlaylist(final String playlistUrl) {
        return null;
    }
}
