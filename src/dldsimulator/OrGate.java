package dldsimulator;
public class OrGate extends CompositeLogicGate {
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
	@Override
	protected boolean compositeLogicGateOutput() {
		return parent1.output() || parent2.output();
	}
}
