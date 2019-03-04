package com.waen.waen.Admin.View;

import com.waen.waen.Admin.Model.Absences_Details;

import java.util.List;

public interface GetAbsences_View {

    void ListAbseces(List<Absences_Details> list);
    void Error();
}
