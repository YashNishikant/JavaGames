
public class Space {

	private String character;
	private boolean empty;
	private String replaceStr;
	private boolean endTile;
	
	public Space(String c) {
		if(c.equals("")) {
			empty = true;
		}
		else {
			empty = false;
		}
	}

	public boolean getEnd() {
		return endTile;
	}
	public void setEnd(boolean x) {
		endTile = x;
	}
	public String getChar() {
		return character;
	}
	public String getReplaceStr() {
		return replaceStr;
	}
	public void setReplaceStr(String x) {
		replaceStr = x;
	}
	public void setChar(String x) {
		character = x;
	}
	
	public void setEmpty(boolean x) {
		empty = x;
	}
	
	public boolean isEmpty() {
		return empty;
	}
}
