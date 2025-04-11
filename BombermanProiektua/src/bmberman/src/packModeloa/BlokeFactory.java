package bmberman.src.packModeloa;

public class BlokeFactory {
	private static BlokeFactory nBlokeFactory;
	private BlokeFactory() {
	}
	
	public static BlokeFactory getBlokeFactory() {
		if (nBlokeFactory == null) {
			nBlokeFactory = new BlokeFactory();
		}
		return nBlokeFactory;
	}

    public Bloke createBloke(String type) {
    	Bloke bloke = null;
        if (type.equals("gogorra") ) {
            bloke = new BlokeGogorra();
        }else if(type.equals("biguna")) {
            	bloke = new BlokeBiguna();
        }
        return bloke;
    }
}
