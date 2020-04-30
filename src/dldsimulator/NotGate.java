package dldsimulator;
public class NotGate implements LogicOutputer {
	private LogicOutputer parentLogicOutputter;
        int pointx, pointy, pointx1, pointy1, pointx2, pointy2, count;
 
        public int Getpointx(){
            return pointx;
        }
        public int Getpointy(){
            return pointy;
        }
        public int Getpointx1(){
            return pointx1;
        }
        public int Getpointy1(){
            return pointy1;
        }
        public int Getpointx2(){
            return pointx2;
        }
        public int Getpointy2(){
            return pointy2;
        }
        public int Getcount(){
            return count;
        }
	
	public NotGate() {
		parentLogicOutputter = null;
	}
	
	public void setParentConnection(LogicOutputer parent) {
		if (this.parentLogicOutputter != null)
			throw new RuntimeException("Parent already set");
		
		this.parentLogicOutputter = parent;
	}
	
	@Override
	public boolean output() {
		if (this.parentLogicOutputter == null)
			throw new RuntimeException("Parents not properly set");
		
		return !parentLogicOutputter.output();
	}
}
