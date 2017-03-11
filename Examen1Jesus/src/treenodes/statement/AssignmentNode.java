package treenodes.statement;

import code_generation.ExpresionCode;
import code_generation.LabelFactory;
import treenodes.expresion.ExpresionNode;
import treenodes.expresion.IdNode;
import treenodes.expresion.MultiplyOperatorNode;
import treenodes.expresion.NumberNode;

/**
 * Created by mac on 5/7/15.
 */
public class AssignmentNode extends StatementNode {
    ExpresionNode value;
    IdNode id;


    public AssignmentNode(IdNode id,ExpresionNode value) {
        this.value = value;
        this.id = id;
    }

    public ExpresionNode getValue() {
        return value;
    }

    public void setValue(ExpresionNode value) {
        this.value = value;
    }

    public IdNode getId() {
        return id;
    }

    public void setId(IdNode id) {
        this.id = id;
    }

    @Override
    public String generateCode() {
        ExpresionCode valueCode;
        ExpresionCode rigthIndexCode = null;

        if (this.value instanceof IdNode && ((IdNode)value).getIndex() != null){
            rigthIndexCode = ((IdNode)value).getIndex().generateCode();
            valueCode = value.generateCode();
        }else{
            valueCode = value.generateCode();
        }

        if (id.getIndex() == null){
            if(rigthIndexCode == null){
                return valueCode.getCode() +
                        id.getName()+":="+valueCode.getResultVar()+"\n";
            }
            return valueCode.getCode() +
                    id.getName()+":="+valueCode.getResultVar()+"\n";
        }else{
            ExpresionCode indexCode = id.getIndex().generateCode();

            ExpresionCode temp = new MultiplyOperatorNode(new IdNode(indexCode.getResultVar()),new NumberNode(4)).generateCode();

            if (rigthIndexCode == null){

                String resultVar = LabelFactory.createLabel("t");
                String code = resultVar+":="+indexCode.getResultVar();
                ExpresionCode temp2 = new ExpresionCode(code,resultVar);

                return indexCode.getCode()+"\n"+valueCode.getCode() + temp2.getCode()+"\n" +
                        id.getName()+"["+temp2.getResultVar()+"]"+":="+valueCode.getResultVar()+"\n";

                /*return indexCode.getCode()+"\n"+valueCode.getCode() +
                        id.getName()+"["+indexCode.getResultVar()+"]"+":="+valueCode.getResultVar()+"\n"; */
            }

           // +valueCode.getResultVar()+"["+rigthIndexCode.getResultVar()+"]"+"

            String resultVar2 = LabelFactory.createLabel("t");
           // String code2 = resultVar2+":="+rigthIndexCode.getResultVar();
           // ExpresionCode temp2 = new ExpresionCode(code2,resultVar2);
            ExpresionCode temp2 = new MultiplyOperatorNode(
                    new IdNode(rigthIndexCode.getResultVar()),new NumberNode(4)).generateCode();

            String resultVar = LabelFactory.createLabel("t");
            String code = resultVar+":="+valueCode.getResultVar()+"["+temp2.getResultVar()+"]";
            ExpresionCode right = new ExpresionCode(code,resultVar);

            return indexCode.getCode()+ rigthIndexCode.getCode() + valueCode.getCode() + temp.getCode() +temp2.getCode()+ right.getCode() +"\n"+
                    id.getName()+"["+temp.getResultVar()+"]"+":="+right.getResultVar();
        }
    }
}
