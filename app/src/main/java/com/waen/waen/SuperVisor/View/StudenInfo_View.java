package com.waen.waen.SuperVisor.View;

import com.waen.waen.SuperVisor.Model.StudentDetails;
import com.waen.waen.SuperVisor.Model.StudentsInfo;

import java.util.List;

/**
 * Created by Ahmed on 25/12/2018.
 */

public interface StudenInfo_View {

    void GetStudents(List<StudentDetails> list);
    void Error();
}
