package treenodes.statement;

import treenodes.expresion.ExpresionNode;

/**
 * Created by mac on 5/7/15.
 */
public class PrintNode extends StatementNode {
    ExpresionNode valueToPrint;

    public PrintNode(ExpresionNode valueToPrint) {
        this.valueToPrint = valueToPrint;
    }

    public ExpresionNode getValueToPrint() {
        return valueToPrint;
    }

    public void setValueToPrint(ExpresionNode valueToPrint) {
        this.valueToPrint = valueToPrint;
    }

    @Override
    public String generateCode() {
        return null;
    }
}
