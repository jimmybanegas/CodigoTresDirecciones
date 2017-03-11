package treenodes.expresion;

import code_generation.ExpresionCode;

/**
 * Created by mac on 5/7/15.
 */
public abstract   class BinaryOperatorNode extends ExpresionNode {

       ExpresionNode leftOperand;
       ExpresionNode rightOperand;

    public BinaryOperatorNode(ExpresionNode leftOperand, ExpresionNode rightOperand) {
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
    }

    public ExpresionNode getLeftOperand() {
        return leftOperand;
    }

    public void setLeftOperand(ExpresionNode leftOperand) {
        this.leftOperand = leftOperand;
    }

    public ExpresionNode getRightOperand() {
        return rightOperand;
    }

    public void setRightOperand(ExpresionNode rightOperand) {
        this.rightOperand = rightOperand;
    }


}
