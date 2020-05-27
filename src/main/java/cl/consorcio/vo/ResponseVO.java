package cl.consorcio.vo;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ResponseVO<T>{

    static Logger logger = LogManager.getLogger(ResponseVO.class);

    @JsonProperty("codigo")
    private String codigo;

    @JsonProperty("mensaje")
    private String mensaje;

    @JsonProperty("body")
    private T body;

    public ResponseVO() {
    }

    public ResponseVO(String codigo, String mensaje) {
        this.codigo = codigo;
        this.mensaje = mensaje;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        ResponseVO.logger = logger;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
