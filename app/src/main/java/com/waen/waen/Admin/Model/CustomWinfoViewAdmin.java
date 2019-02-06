package com.waen.waen.Admin.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.waen.waen.R;
import com.waen.waen.SuperVisor.Model.InfoWindowData;

public class CustomWinfoViewAdmin implements GoogleMap.InfoWindowAdapter {
    private View viewe;
    private Context comtec;

    public CustomWinfoViewAdmin(Context context) {
        this.comtec = context;
        viewe = LayoutInflater.from(context).inflate(R.layout.windowinfogoogleadmin, null);
    }

    private void rendowWindowText(Marker marker, View view) {

        String title = marker.getTitle();
        TextView tvTitle = view.findViewById(R.id.T_bussName);

        if (!title.equals("")) {
            tvTitle.setText(title);
        }

        String snippet = marker.getSnippet();
        TextView tvSnippet = view.findViewById(R.id.T_bussNumber);

        if (!snippet.equals("")) {
            tvSnippet.setText(snippet);
        }
        BusDetail infoWindowData = (BusDetail) marker.getTag();

//        int imageId = comtec.getResources().getIdentifier(infoWindowData.getImage().toLowerCase(),
//                "drawable", context.getPackageName());
//        img.setImageResource(imageId);
        TextView SuperVisor = view.findViewById(R.id.T_SuperVisor);
        TextView Speed = view.findViewById(R.id.T_speeed);

        SuperVisor.setText(infoWindowData.getSupervisorName());
        Speed.setText(infoWindowData.getSpeed()+"");


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
