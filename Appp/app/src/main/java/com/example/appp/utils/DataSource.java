package com.example.appp.utils;

import com.example.appp.R;
import com.example.appp.models.Movie;

import java.util.ArrayList;
import java.util.List;

public class DataSource {
    public static List<Movie> getPopularMovies(){

        List<Movie> lstMovies = new ArrayList<>();
        lstMovies.add(new Movie("Mortal Kombat", R.drawable.mortalkombat,R.drawable.kombat));
        lstMovies.add(new Movie("Tom and Jerry",R.drawable.tomandjerry,R.drawable.jerry));
        lstMovies.add(new Movie("Cherry",R.drawable.cherry,R.drawable.bgcherry));
        lstMovies.add(new Movie("Godzilla vs Kong",R.drawable.godzillakong,R.drawable.bgkong));
        lstMovies.add(new Movie("365 Ngày Yêu Em",R.drawable.ngayyeu,R.drawable.bg365));
        lstMovies.add(new Movie("Rise Of The Mummy",R.drawable.riseofthemummy,R.drawable.bgmummy));
        lstMovies.add(new Movie("The acedamy of magic",R.drawable.theacademyofmagic,R.drawable.bgmagic));
        lstMovies.add(new Movie("The Dissident",R.drawable.thedissident,R.drawable.bgdissent));
        lstMovies.add(new Movie("Let Him Go",R.drawable.lethimho,R.drawable.bglethimgo));
        return lstMovies;
    }

    public static List<Movie> getWeekMovies(){

        List<Movie> lstMovies = new ArrayList<>();
        /*lstMovies.add(new Movie("Mortal Kombat", R.drawable.mortalkombat,R.drawable.kombat));
        lstMovies.add(new Movie("Tom and Jerry",R.drawable.tomandjerry,R.drawable.jerry));
        lstMovies.add(new Movie("Cherry",R.drawable.cherry,R.drawable.bgcherry));
        lstMovies.add(new Movie("Godzilla vs Kong",R.drawable.godzillakong,R.drawable.bgkong));
        lstMovies.add(new Movie("365 Ngày Yêu Em",R.drawable.ngayyeu,R.drawable.bg365));*/
        lstMovies.add(new Movie("Rise Of The Mummy",R.drawable.riseofthemummy,R.drawable.bgmummy));
        lstMovies.add(new Movie("The acedamy of magic",R.drawable.theacademyofmagic,R.drawable.bgmagic));
        lstMovies.add(new Movie("The Dissident",R.drawable.thedissident,R.drawable.bgdissent));
        lstMovies.add(new Movie("Let Him Go",R.drawable.lethimho,R.drawable.bglethimgo));
        return lstMovies;

    }
}
