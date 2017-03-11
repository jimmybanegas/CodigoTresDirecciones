package treenodes.expresion;

import code_generation.ExpresionCode;

/**
 * Created by mac on 5/7/15.
 */
public  class NumberNode extends ExpresionNode {
    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public NumberNode(Integer value) {
        this.value = value;
    }

    Integer value;

    @Override
    public ExpresionCode generateCode() {
        return new ExpresionCode("",value.toString());
    }
}
