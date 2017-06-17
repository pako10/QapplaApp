package com.example.paco.qapplaapp.Objects;

/**
 * Created by paco on 16/04/2017.
 */

public class FriendRequest {

    /** SI LE DA CLICK AL LIST DE ESTE USUARIO SE LLEVARA AL PERFIL DE LA PERSONA QUE SOLICITA, USAMOS SU ID PARA BUSCAR SU PERFIL**/

    String id; //user UID
    String userName;
    String requestResponse; /**DONDE 0 ES COMO SE MANDA 1 ACEPTADO Y 2 RECHAZADO, SI ES 2 SE BORRA LA SOLICITUD*/

    public FriendRequest() {
    }

    public FriendRequest(String id, String userName, String requestResponse) {
        this.id = id;
        this.userName = userName;
        this.requestResponse = requestResponse;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRequestResponse() {
        return requestResponse;
    }

    public void setRequestResponse(String requestResponse) {
        this.requestResponse = requestResponse;
    }
}
