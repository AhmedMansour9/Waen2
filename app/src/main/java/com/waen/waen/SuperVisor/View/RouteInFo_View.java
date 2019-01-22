package com.waen.waen.SuperVisor.View;

import com.waen.waen.SuperVisor.Model.InFo;
import com.waen.waen.SuperVisor.Model.Routes_Details;

import java.util.List;

/**
 * Created by Ahmed on 23/12/2018.
 */

public interface RouteInFo_View {

    void getlist(Routes_Details  data);
    void getlistInFo(List<InFo> list);
    void Error();
}
