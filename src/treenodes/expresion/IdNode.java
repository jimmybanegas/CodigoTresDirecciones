package treenodes.expresion;

import code_generation.ExpresionCode;

/**
 * Created by mac on 5/7/15.
 */
public  class IdNode extends ExpresionNode {
      String name;
      private ExpresionNode index;

    public IdNode(String name, ExpresionNode index) {
        this.name = name;
        this.index = index;
    }

    public IdNode(String name) {
        this.name = name;
        this.index = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public ExpresionCode generateCode() {
        return new ExpresionCode("",name) ;
    }

    public ExpresionNode getIndex() {
        return index;
    }

    public void setIndex(ExpresionNode index) {
        this.index = index;
    }
}
