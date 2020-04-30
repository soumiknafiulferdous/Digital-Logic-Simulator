
package dldsimulator;

public class DirectLogicOutputter implements LogicOutputer {
	private Boolean output;
        
	public int Getpointx(){
            return 0;
        }
        public int Getpointy(){
             return 0;
        }
        public int Getpointx1(){
             return 0;
        }
        public int Getpointy1(){
             return 0;
        }
        public int Getpointx2(){
             return 0;
        }
        public int Getpointy2(){
            return 0;
        }
        public int Getcount(){
            return 0;
        }
        
	public DirectLogicOutputter(boolean output) {
		this.output = output;
	}

	@Override
	public boolean output() {
		return this.output;
	}
}
