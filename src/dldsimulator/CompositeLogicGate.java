package dldsimulator;

import javax.swing.JOptionPane;

public abstract class CompositeLogicGate implements LogicOutputer {
	protected LogicOutputer parent1, parent2;
        
        @Override
        public int Getpointx(){
            return 0;
        }
        @Override
        public int Getpointy(){
             return 0;
        }
        @Override
        public int Getpointx1(){
             return 0;
        }
        @Override
        public int Getpointy1(){
             return 0;
        }
        @Override
        public int Getpointx2(){
             return 0;
        }
        @Override
        public int Getpointy2(){
            return 0;
        }
        @Override
        public int Getcount(){
            return 0;
        }
	public CompositeLogicGate() {
		parent1 = null;
		parent2 = null;
	}
	public void addParentConnection(LogicOutputer parent) {
            try{
		if (this.parent1 == null) {
			this.parent1 = parent;
			return;
		}
		if (this.parent2 == null) {
			this.parent2 = parent;
			return;
		}
                throw new RuntimeException();
                
            }
            catch(RuntimeException e){
		JOptionPane.showMessageDialog(null,"Both parents already set");
            }
	}
	@Override
	public boolean output() {
		if (this.parent1 == null || this.parent2 == null)
			throw new RuntimeException("Parents aren't set properly");
		return compositeLogicGateOutput();
	}
	abstract protected boolean compositeLogicGateOutput();
}