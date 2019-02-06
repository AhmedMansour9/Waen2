package com.waen.waen.SuperVisor.View;

import com.waen.waen.SuperVisor.Model.Inbox_details;

import java.util.List;

public interface Messages_Inbox_View {

    void GetMessagesParent(List<Inbox_details> inbox_details);
    void SendMessages_SuperVisor(List<Inbox_details> inbox_details);
    void Inbox_Parent_Admin(List<Inbox_details> inbox_details);
    void Inbox_SuperVisor_Admin(List<Inbox_details> inbox_details);

    void Sent_Parent_Admin(List<Inbox_details> inbox_details);
    void Sent_SuperVisor_Admin(List<Inbox_details> inbox_details);
    void Error();
}
