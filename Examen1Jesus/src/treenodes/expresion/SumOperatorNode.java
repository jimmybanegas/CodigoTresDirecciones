package treenodes.expresion;

import code_generation.ExpresionCode;
import code_generation.LabelFactory;

/**
 * Created by mac on 5/7/15.
 */
public    class SumOperatorNode extends BinaryOperatorNode {

    public SumOperatorNode(ExpresionNode leftOperand, ExpresionNode rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public ExpresionCode generateCode() {
        ExpresionCode rightCode ;
        ExpresionCode leftCode;
        ExpresionCode rightCodeIndex ;
        ExpresionCode leftCodeIndex;
        ExpresionCode temp1 = null;
        ExpresionCode temp2 = null;

        if(leftOperand instanceof IdNode && ((IdNode) leftOperand).getIndex() != null){
            leftCodeIndex = ((IdNode)leftOperand).getIndex().generateCode();

            temp1 = new MultiplyOperatorNode(new IdNode(leftCodeIndex.getResultVar()),new NumberNode(4)).generateCode();

           // System.out.println("Leftindex code: "+temp1.getCode() + " Leftindex var: "+temp1.getResultVar());
        }
        leftCode = leftOperand.generateCode();

        if (rightOperand instanceof IdNode && ((IdNode) rightOperand).getIndex() != null){
            rightCodeIndex = ((IdNode)rightOperand).getIndex().generateCode();
            temp2 = new MultiplyOperatorNode(new IdNode(rightCodeIndex.getResultVar()),new NumberNode(4)).generateCode();
         //   System.out.println("Rightindex code: "+temp2.getCode() + " Rightindex var: "+temp2.getResultVar());
        }

        rightCode = rightOperand.generateCode();

        String resultVar = LabelFactory.createLabel("t");

        String code = "";

        if(temp1!= null && temp2 != null){
            String resultVar2 = LabelFactory.createLabel("t");
            String code2 = resultVar2+":="+temp1.getResultVar();
            ExpresionCode temp3 = new ExpresionCode(code2,resultVar2);

            String resultVar3 = LabelFactory.createLabel("t");
            String code3 = resultVar3+":="+temp2.getResultVar();
            ExpresionCode temp4 = new ExpresionCode(code3,resultVar3);

            String resultVar4 = LabelFactory.createLabel("t");
            String code4 = resultVar4+":="+rightCode.getResultVar()+"["+temp3.getResultVar()+ "]";
            ExpresionCode temp5 = new ExpresionCode(code4,resultVar4);

            String resultVar5 = LabelFactory.createLabel("t");
            String code5 = resultVar5+":="+leftCode.getResultVar()+"["+temp4.getResultVar()+ "]";
            ExpresionCode temp6 = new ExpresionCode(code5,resultVar5);

            code = leftCode.getCode()+
                    rightCode.getCode()+temp1.getCode()+temp2.getCode()+temp3.getCode()+ "\n"+ temp4.getCode()
                    +"\n" +temp5.getCode() + "\n" + temp6.getCode()+"\n"+
                    resultVar+":=" +temp6.getResultVar() +"+" + temp5.getResultVar() + "\n";

        } else if (temp1 != null){
            String resultVar2 = LabelFactory.createLabel("t");
            String code2 = resultVar2+":="+temp1.getResultVar();
            ExpresionCode temp3 = new ExpresionCode(code2,resultVar2);


            String resultVar4 = LabelFactory.createLabel("t");
            String code4 = resultVar4+":="+rightCode.getResultVar()+"["+temp3.getResultVar()+ "]";
            ExpresionCode temp5 = new ExpresionCode(code4,resultVar4);


            code = leftCode.getCode()+
                    rightCode.getCode()+temp1.getCode()+temp3.getCode()+ "\n"+
                    temp5.getCode() + "\n" + leftCode.getResultVar()+":=" +"+" + temp5.getResultVar() + "\n";

            /*code = leftCode.getCode()+
                    rightCode.getCode()+ temp1.getCode()+
                    resultVar+":="+leftCode.getResultVar()+"+"+rightCode.getResultVar()+"["+temp1.getResultVar()+ "]"+"\n"; */
        }else if(temp2 != null){

            String resultVar3 = LabelFactory.createLabel("t");
            String code3 = resultVar3+":="+temp2.getResultVar();
            ExpresionCode temp4 = new ExpresionCode(code3,resultVar3);

            String resultVar5 = LabelFactory.createLabel("t");
            String code5 = resultVar5+":="+leftCode.getResultVar()+"["+temp4.getResultVar()+ "]";
            ExpresionCode temp6 = new ExpresionCode(code5,resultVar5);

            code = leftCode.getCode()+
                    rightCode.getCode()+temp2.getCode()+ "\n"+ temp4.getCode()
                    +"\n" + temp6.getCode()+"\n"+
                    resultVar+":=" +temp6.getResultVar() +"+" + rightCode.getResultVar()+ "\n";
            /* code = leftCode.getCode()+
                    rightCode.getCode()+ temp2.getCode()+
                    resultVar+":="+leftCode.getResultVar()+"["+temp2.getResultVar()+ "]" + "+"+rightCode.getResultVar()+"\n"; */
        }
        else{
            code = leftCode.getCode()+
                    rightCode.getCode()+
                    resultVar+":="+leftCode.getResultVar()+"+"+rightCode.getResultVar()+"\n";
        }

        return new ExpresionCode(code,resultVar);
    }
}
