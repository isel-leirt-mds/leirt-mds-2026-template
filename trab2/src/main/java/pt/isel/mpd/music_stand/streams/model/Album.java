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

package pt.isel.mpd.music_stand.streams.model;

import java.util.function.Supplier;
import java.util.stream.Stream;

import static pt.isel.mpd.utils.resources.BuildUtils.TO_COMPLETE;

public class Album {
    private final String name;
    private final String mbid;
    private  int playCount;
    private int listeners;

    public Album(String name,  String mbid,
                  int playCount, int listeners,
                  Supplier<Stream<Track>> tracks) {
        this.name = name;
       
        this.mbid = mbid;
        TO_COMPLETE("Album Tracks");

    }

    public String getName() {
        return name;
    }

   
    public String getMbid() {
        return mbid;
    }

    public int getPlayCount() {
        return playCount;
    }

    public int getListeners() {
        return listeners;
    }

    public Stream<Track> getTracks() {
        TO_COMPLETE("getTracks");
        return null;
    }

    @Override
    public String toString() {
        return "{ " +
                "name=" + getName() +
                ", mbid=" + getMbid() +
                ", playCount=" + getPlayCount() +
                ", listeners=" + getListeners() +
                "}";
    }
}
