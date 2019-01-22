package com.waen.waen.Main.View;

import com.waen.waen.Main.Model.LoginInFo;

/**
 * Created by Ahmed on 17/12/2018.
 */

public interface LoginView {

    void OpenRole(LoginInFo loginInF);

    void showError(String error);
    void Invalidemail(String password);


}
