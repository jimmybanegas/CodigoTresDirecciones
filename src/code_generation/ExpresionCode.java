package code_generation;

/**
 * Created by mac on 3/2/17.
 */
public class ExpresionCode {
    String code;
    String resultVar;

    public ExpresionCode(String code, String resultVar) {
        this.code = code;
        this.resultVar = resultVar;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getResultVar() {
        return resultVar;
    }

    public void setResultVar(String resultVar) {
        this.resultVar = resultVar;
    }
}
