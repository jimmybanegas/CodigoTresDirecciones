package treenodes.expresion;

import code_generation.ExpresionCode;
import code_generation.LabelFactory;
import treenodes.statement.StatementNode;

import java.util.List;

/**
 * Created by mac on 5/7/15.
 */
public class ForNode extends StatementNode {
    IdNode iteratorVariable;
    ExpresionNode initialValue;
    ExpresionNode finalValue;
    List<StatementNode> code;

    public ForNode(IdNode iteratorVariable, ExpresionNode initialValue, ExpresionNode finalValue, List<StatementNode> code) {
        this.iteratorVariable = iteratorVariable;
        this.initialValue = initialValue;
        this.finalValue = finalValue;
        this.code = code;
    }

    public IdNode getIteratorVariable() {
        return iteratorVariable;
    }

    public void setIteratorVariable(IdNode iteratorVariable) {
        this.iteratorVariable = iteratorVariable;
    }

    public ExpresionNode getInitialValue() {
        return initialValue;
    }

    public void setInitialValue(ExpresionNode initialValue) {
        this.initialValue = initialValue;
    }

    public ExpresionNode getFinalValue() {
        return finalValue;
    }

    public void setFinalValue(ExpresionNode finalValue) {
        this.finalValue = finalValue;
    }

    public List<StatementNode> getCode() {
        return code;
    }

    public void setCode(List<StatementNode> code) {
        this.code = code;
    }

    @Override
    public String generateCode() {
        String beginForLbl = LabelFactory.createLabel("for");
        String endForLbl = LabelFactory.createLabel("endfor");
        ExpresionCode initialCode = initialValue.generateCode();
        ExpresionCode finalCode = finalValue.generateCode();
        String codeManager ="";
        for(StatementNode stmt: code) {
            codeManager+= stmt.generateCode();
        }


        return initialCode.getCode()+
                iteratorVariable.getName()+":="+initialCode.getResultVar()+"\n"+
                finalCode.getCode() +
                beginForLbl+":\n" +
                "if "+iteratorVariable.getName()+">="+finalCode.getResultVar()+" goto "+endForLbl+"\n"+
                codeManager+
                iteratorVariable.getName()+":="+iteratorVariable.getName()+"+1;\n"+
                "goto "+beginForLbl+"\n"+
                endForLbl+":\n";

    }
}
