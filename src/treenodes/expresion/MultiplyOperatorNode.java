package treenodes.expresion;

import code_generation.ExpresionCode;
import code_generation.LabelFactory;

/**
 * Created by mac on 5/7/15.
 */
public    class MultiplyOperatorNode extends BinaryOperatorNode {

    public MultiplyOperatorNode(ExpresionNode leftOperand, ExpresionNode rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public ExpresionCode generateCode() {
        ExpresionCode leftCode = leftOperand.generateCode();
        ExpresionCode rightCode = rightOperand.generateCode();
        String resultVar = LabelFactory.createLabel("t");
        String code = leftCode.getCode()+
                rightCode.getCode()+
                resultVar+":="+leftCode.getResultVar()+"*"+rightCode.getResultVar()+"\n";
        return new ExpresionCode(code,resultVar);
    }
}
