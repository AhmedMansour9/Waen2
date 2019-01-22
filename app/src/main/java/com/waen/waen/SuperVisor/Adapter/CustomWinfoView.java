package com.waen.waen.SuperVisor.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.waen.waen.R;
import com.waen.waen.SuperVisor.Model.InfoWindowData;

/**
 * Created by Ahmed on 26/12/2018.
 */

public class    CustomWinfoView implements GoogleMap.InfoWindowAdapter {
    private  View viewe;
    private Context comtec;
    public CustomWinfoView(Context context){
        this.comtec=context;
        viewe= LayoutInflater.from(context).inflate(R.layout.windowsinfogooglemap,null);
    }
    private void rendowWindowText(Marker marker, View view){

        String title = marker.getTitle();
        TextView tvTitle =view.findViewById(R.id.T_StudentName);

        if(!title.equals("")){
            tvTitle.setText(title);
        }

        String snippet = marker.getSnippet();
        TextView tvSnippet =view.findViewById(R.id.T_StudentAddress);

        if(!snippet.equals("")){
            tvSnippet.setText(snippet);
        }
        InfoWindowData infoWindowData = (InfoWindowData) marker.getTag();

//        int imageId = comtec.getResources().getIdentifier(infoWindowData.getImage().toLowerCase(),
//                "drawable", context.getPackageName());
//        img.setImageResource(imageId);
        TextView ParentPhone =view.findViewById(R.id.T_ParentPhone);
        TextView ParentAdress =view.findViewById(R.id.T_ParentAddress);

        ParentPhone.setText(infoWindowData.getParentPhone());
        ParentAdress.setText(infoWindowData.getParentAddress());


    }
    @Override
    public View getInfoWindow(Marker marker) {
        rendowWindowText(marker, viewe);

        return viewe;
    }

    @Override
    public View getInfoContents(Marker marker) {
        rendowWindowText(marker, viewe);

        return viewe;
    }
}