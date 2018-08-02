package com.javi.spotify.service;

import com.javi.spotify.client.model.Album;
import com.javi.spotify.client.model.Albums;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class SaveAlbumService {
    private MongoTemplate mongoTemplate;

    /**
     * Constructor.
     *
     * @param mongoTemplate {@link MongoTemplate}
     */
    public SaveAlbumService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    /**
     * @param albums {@link Albums}
     */
    private void saveAlbums(Albums albums) {
        albums
            .getItems()
            .forEach((Album album) -> { // todo: Optimize, this is really slow. Complexity: 2n
                Album albumInDB = getAlbumBySpotifyId(album.getSpotifyId());

                if (albumInDB != null) { // Already exist, so...
                    mongoTemplate.save(albumInDB); // update!
                } else { // Does not exist, so...
                    mongoTemplate.save(album); //  save!
                }
            });
    }

    /**
     * This save albums in a second thread.
     *
     * @param albums {@link Albums} to save.
     */
    @Async
    public void saveAlbumsAsync(Albums albums) {
        this.saveAlbums(albums);
    }

    /**
     * @param spotifyId {@link String}
     * @return a {@link Album} if was found, or null if is not.
     */
    private Album getAlbumBySpotifyId(String spotifyId) {
        Criteria condition = Criteria.where("spotifyId").is(spotifyId);
        return mongoTemplate.findOne(Query.query(condition), Album.class);
    }
}
