package com.example.appp.mainmusic;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.appp.R;
import com.example.appp.adaptermusic.Adapter_listbaihat;
import com.example.appp.listmusic.list_baihat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Activity_bai_hat extends AppCompatActivity {
    TextView txt_tencasi,txt_tenbaihatdangphat,txt_tgdangphat,txt_tgketthuc;
    ImageButton IMGB_play,IMGB_next,IMGB_re;
    ListView listView_baihat;
    SeekBar seekBar;

    int vitri;

    ArrayList<list_baihat> list_baihats;
    MediaPlayer player=new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai_hat);
        Intent intent = getIntent();
        int vt = intent.getIntExtra("position", 0);
        String cs = intent.getStringExtra("tencasi");
        txt_tencasi = (TextView) findViewById(R.id.txt_casi);
        txt_tenbaihatdangphat = (TextView) findViewById(R.id.txt_dang_phat);
        txt_tgdangphat = (TextView) findViewById(R.id.txt_time_play);
        txt_tgketthuc = (TextView) findViewById(R.id.txt_time_out);
        IMGB_next = (ImageButton) findViewById(R.id.bt_next);
        IMGB_play = (ImageButton) findViewById(R.id.bt_pause);
        IMGB_re = (ImageButton) findViewById(R.id.bt_re);
        listView_baihat = (ListView) findViewById(R.id.list_nhac);
        seekBar = (SeekBar) findViewById(R.id.seebar);

        txt_tencasi.setText("Ca sĩ:" + cs);
        list_baihats = new ArrayList<list_baihat>();

        switch (vt) {
            case 0:
                list_baihats.add(new list_baihat("Anh Khác Hay Em Khác",time(R.raw.khacviet_anh_khac_hay_em_khac),R.raw.khacviet_anh_khac_hay_em_khac));
                list_baihats.add(new list_baihat("Anh Yêu Người Khác Rồi",time(R.raw.khacviet_anh_yeu_nguoi_khac_roi),R.raw.khacviet_anh_yeu_nguoi_khac_roi));
                break;
            case 1:
                list_baihats.add(new list_baihat("Bỏ Lỡ Một Người",time(R.raw.lebaobinh_bo_lo_mot_nguoi),R.raw.lebaobinh_bo_lo_mot_nguoi));
                list_baihats.add(new list_baihat("Lá Xa Lìa Cành",time(R.raw.lebaobinh_la_xa_lia_canh),R.raw.lebaobinh_la_xa_lia_canh));
                break;
            case 2:
                list_baihats.add(new list_baihat("Anh Muốn",time(R.raw.themen_anh_muon),R.raw.themen_anh_muon));
                list_baihats.add(new list_baihat("Em Luôn Ở Trong Tâm Trí Anh",time(R.raw.themen_em_luon_trong_tam_tri_anh),R.raw.themen_em_luon_trong_tam_tri_anh));
                break;
            case 3:
                list_baihats.add(new list_baihat("Anh Nhớ Em",time(R.raw.tuanhung_anh_nho_em),R.raw.tuanhung_anh_nho_em));
                list_baihats.add(new list_baihat("Phải Chia Tay Thôi",time(R.raw.tuanhung_phai_chia_tay_thoi),R.raw.tuanhung_phai_chia_tay_thoi));
                break;
        }
        Adapter_listbaihat adapter_listbaihat=new Adapter_listbaihat(Activity_bai_hat.this,R.layout.list_baihat,list_baihats);
        listView_baihat.setAdapter(adapter_listbaihat);
        khoitao();
        player.start();
        IMGB_play.setImageResource(R.drawable.pause);

        timenow();
        listView_baihat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(player.isPlaying()){
                    player.stop();
                    vitri=position;
                    khoitao();
                }
                IMGB_play.setImageResource(R.drawable.pause);
            }
        });
        IMGB_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(player.isPlaying()){
                    player.pause();
                    IMGB_play.setImageResource(R.drawable.play);
                }else
                {
                    player.start();
                    IMGB_play.setImageResource(R.drawable.pause);
                }
            }
        });
        IMGB_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vitri++;
                if(vitri>(list_baihats.size()-1)){
                    vitri=0;
                }
                player.stop();
                khoitao();
            }
        });
        IMGB_re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vitri--;
                if(vitri<0){
                    vitri=list_baihats.size()-1;
                }
                player.stop();
                khoitao();
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                player.seekTo(seekBar.getProgress());
            }
        });
    }
    void timenow(){
        Handler handler=new Handler();
        boolean b=handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("mm:ss");
                txt_tgdangphat.setText(simpleDateFormat.format(player.getCurrentPosition()));
                seekBar.setProgress(player.getCurrentPosition());
                player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        vitri++;
                        if(vitri>list_baihats.size()-1){
                            vitri=0;
                            player.stop();
                            IMGB_play.setBackgroundResource(R.drawable.play);
                        }else {
                            player.stop();
                            khoitao();
                            player.start();
                        }
                    }
                });
                handler.postDelayed(this,500);
            }
        },100);
    }
    private void khoitao(){
        player=MediaPlayer.create(Activity_bai_hat.this,list_baihats.get(vitri).baihat);
        txt_tenbaihatdangphat.setText("Đang Phát:"+list_baihats.get(vitri).tenbaihat);
        txt_tgketthuc.setText(time(list_baihats.get(vitri).baihat ));
        seekBar.setMax(player.getDuration());
        player.start();
    }
    private String time(int baihat){
        String t;
        MediaPlayer player=new MediaPlayer();
        player=MediaPlayer.create(Activity_bai_hat.this,baihat);
        SimpleDateFormat tg=new SimpleDateFormat("mm:ss");
        t=tg.format(player.getDuration());
        return t;
    }

    @Override
    protected void onStop() {
        player.stop();
        super.onStop();
    }
}