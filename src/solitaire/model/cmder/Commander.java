package solitaire.model.cmder;

public interface Commander {

	public boolean execute();
	public void undo();

}
