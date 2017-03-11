package treenodes.expresion;

import code_generation.ExpresionCode;

/**
 * Created by mac on 5/7/15.
 */
public    class DivideOperatorNode extends BinaryOperatorNode {

    public DivideOperatorNode(ExpresionNode leftOperand, ExpresionNode rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public ExpresionCode generateCode() {
        return null;
    }
}
