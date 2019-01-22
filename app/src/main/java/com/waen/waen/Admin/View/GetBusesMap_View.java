package com.waen.waen.Admin.View;

import com.waen.waen.Admin.Model.GetBusesInfo;

import java.util.List;

public interface GetBusesMap_View {

    void ListBuses(List<GetBusesInfo> list);
    void Error();

}
