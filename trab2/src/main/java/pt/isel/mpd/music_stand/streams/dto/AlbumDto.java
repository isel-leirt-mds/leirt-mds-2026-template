/*
 * GNU General Public License v3.0
 *
 * Copyright (c) 2019, Miguel Gamboa (gamboa.pt)
 *
 *   All rights granted under this License are granted for the term of
 * copyright on the Program, and are irrevocable provided the stated
 * conditions are met.  This License explicitly affirms your unlimited
 * permission to run the unmodified Program.  The output from running a
 * covered work is covered by this License only if the output, given its
 * content, constitutes a covered work.  This License acknowledges your
 * rights of fair use or other equivalent, as provided by copyright law.
 *
 *   You may make, run and propagate covered works that you do not
 * convey, without conditions so long as your license otherwise remains
 * in force.  You may convey covered works to others for the sole purpose
 * of having them make modifications exclusively for you, or provide you
 * with facilities for running those works, provided that you comply with
 * the terms of this License in conveying all material for which you do
 * not control copyright.  Those thus making or running the covered works
 * for you must do so exclusively on your behalf, under your direction
 * and control, on terms that prohibit them from making any copies of
 * your copyrighted material outside their relationship with you.
 *
 *   Conveying under any other circumstances is permitted solely under
 * the conditions stated below.  Sublicensing is not allowed; section 10
 * makes it unnecessary.
 *
 */

package pt.isel.mpd.music_stand.streams.dto;

import java.util.List;

public class AlbumDto {

    private  String name;
    private  int playcount;

    private int listeners;
    private  String mbid;
    private  TracksDto tracks;

    public AlbumDto(String name,
                    int playcount,
                    String mbid,
                    int listeners,
                    TracksDto tracks) {
        this.name = name;
        this.playcount = playcount;
        this.mbid = mbid;
        this.tracks = tracks;
        this.listeners = listeners;
    }

    public String getName() {
        return name;
    }

    public int getPlayCount() {
        return playcount;
    }

    public String getMbid() {
        return mbid;
    }

    public List<TrackDto> getTracks() {
        return tracks.getTracks();
    }
    
    public int getListeners() {
        return listeners;
    }

    public String toString() {
        return "{ " +
                   "name=" + getName() +
                   ", mbid=" + getMbid() +
                   ", listeners = " + getListeners() +
                   ", playCount = " + getPlayCount() +
                   "}";
    }
}
